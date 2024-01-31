package io.openliberty.deepdive.rest.service;

import io.openliberty.deepdive.rest.entities.Student;
import io.openliberty.deepdive.rest.models.StudentDTO;
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
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/students/")
public class ServiceController {
    @Inject
    StudentRepository studentRepository;


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Student.class,
            responseDescription = "A list of students stored within the inventory.",
            responseCode = "200")
    @Operation(
            summary = "List contents.",
            description = "Returns the currently stored student in the inventory.",
            operationId = "getAllStudents")
    public List<StudentDTO> getAllStudents() {
        return studentRepository.getStudents();
    }

    @GET
    @Path("/usernames")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Student.class,
            responseDescription = "A list of students usernames stored within the inventory.",
            responseCode = "200")
    @Operation(
            summary = "List of usernames.",
            description = "Returns the currently stored username student in the inventory.",
            operationId = "listContentsUsernames")
    public List<String> listUsernames() {
        return studentRepository.getStudentsNames();
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
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Student.class,
            responseDescription = "Student with a particular username.",
            responseCode = "200")
    @Operation(
            summary = "Get Student By Username",
            description = "Retrieves and returns the student with a particular username from the database ",
            operationId = "getStudentByUsername"
    )
    public Student getStudentByUsername(
            @Parameter(
                    name = "username", in = ParameterIn.PATH,
                    description = "The email used as username of the student",
                    required = true, example = "dana.petrea@gmail.com",
                    schema = @Schema(type = SchemaType.STRING)
            )
            @PathParam("username") String username) {
        return studentRepository.getStudentByUsername(username);
    }

    @GET
    @Path("/username/{username}/grade")
    @Produces({MediaType.APPLICATION_JSON})
    @APIResponseSchema(value = String.class,
            responseDescription = "The grade for a student with a particular username.",
            responseCode = "200")
    @Operation(
            summary = "Get Grade for a given student username",
            description = "Retrieves and returns the grade of a student with a particular username from the database ",
            operationId = "getGradeStudent"
    )
    public String getGrade(
            @Parameter(
                    name = "username", in = ParameterIn.PATH,
                    description = "The email used as username of the student",
                    required = true, example = "dana.petrea@gmail.com",
                    schema = @Schema(type = SchemaType.STRING)
            )
            @PathParam("username") String username) {
        return studentRepository.getGradeOfAStudent(username);
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
                String username = studentObject.getString("username");
                String grade = studentObject.getString("grade");
                System.out.println(name);
                Student s = studentRepository.getStudentByUsername(username);
                if (s != null) {
                    return fail(username + " already exists.");
                }
                studentRepository.add(name, username, grade);
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