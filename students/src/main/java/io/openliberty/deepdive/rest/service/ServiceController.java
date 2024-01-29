package io.openliberty.deepdive.rest.service;

import io.openliberty.deepdive.rest.model.Student;
import io.openliberty.deepdive.rest.repository.StudentRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.*;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response addStudents(InputStream jsonPayload) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException {
        try {
            StringBuilder jsonContent = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (jsonPayload, StandardCharsets.UTF_8))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    jsonContent.append((char) c);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        } catch (NotSupportedException e) {
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
