package io.openliberty.deepdive.rest.service;

import io.openliberty.deepdive.rest.model.Student;
import io.openliberty.deepdive.rest.repository.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
@Path("/students/")
public class ServiceController {
    @Inject
    StudentRepository studentRepository;

    @GET
    @Path("/{parameter}")
    public String doSomething(@PathParam("parameter") String parameter) {
        return String.format("Processed parameter value '%s'", parameter);
    }

    @GET
    @Path("/hello")
    public String doSomething2() {
        return "Hello";
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Student.class,
            responseDescription = "A list of students stored within the inventory.",
            responseCode = "200")
    @Operation(
            summary = "List contents.",
            description = "Returns the currently stored student in the inventory.",
            operationId = "listContents")
    public List<Student> listContents() {
        return studentRepository.getStudents();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Student.class,
            responseDescription = "Student of a particular name.",
            responseCode = "200")
    @Operation(
            summary = "Get Student",
            description = "Retrieves and returns the student from the database",
            operationId = "getStudent"
    )
    public Student getStudent(
            @Parameter(
                    name = "name", in = ParameterIn.PATH,
                    description = "The name of the student",
                    required = true, example = "Petrea Daniela",
                    schema = @Schema(type = SchemaType.STRING)
            )
            @PathParam("name") String name) {
        return studentRepository.getStudent(name);
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Student.class,
            responseDescription = "Student with a particular email.",
            responseCode = "200")
    @Operation(
            summary = "Get Student",
            description = "Retrieves and returns the student with a particular email from the database ",
            operationId = "getStudentByEmail"
    )
    public Student getStudentByEmail(
            @Parameter(
                    name = "email", in = ParameterIn.PATH,
                    description = "The email of the student",
                    required = true, example = "dana.petrea@gmail.com",
                    schema = @Schema(type = SchemaType.STRING)
            )
            @PathParam("email") String email) {
        return studentRepository.getStudentByEmail(email);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Successfully added student to database"),
            @APIResponse(responseCode = "400",
                    description = "Unable to add student to database")
    })
    @Operation(
            summary = "Add students",
            description = "Add students in database",
            operationId = "addStudents"
    )
    public Response addStudents() throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        try {
            String currentDirectory = System.getProperty("user.dir");
            String filePath = currentDirectory.split("students")[0] + "/students/students.json";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(jsonContent.toString());
            JSONArray studentsArray = jsonObject.getJSONArray("students");
            for (int i = 0; i < studentsArray.length(); i++) {
                JSONObject studentObject = studentsArray.getJSONObject(i);
                String name = studentObject.getString("name");
                String email = studentObject.getString("email");
                String grade = studentObject.getString("grade");
                System.out.println(name);
                Student s = studentRepository.getStudentByEmail(email);
                if (s != null) {
                    return fail(email + " already exists.");
                }
                studentRepository.add(name, email, grade);
            }
        } catch (IOException | jakarta.transaction.NotSupportedException e) {
            e.printStackTrace();
        }
        return success("Students were added in database.");
    }


    private Response success(String message) {
        return Response.ok("{ \"ok\" : \"" + message + "\" }").build();
    }

    private Response fail(String message) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{ \"error\" : \"" + message + "\" }")
                .build();
    }
}
