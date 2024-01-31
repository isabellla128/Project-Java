package com.example.laborator9.retriever;

import com.example.laborator9.services.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@ApplicationScoped
public class StudentsRetriever {
    @Inject
    UserService userService;

    private final String LIBERTY_URL = "http://localhost:9080/students/api/students";

    public String postStudents(InputStream jsonInputStream) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(jsonInputStream, MediaType.APPLICATION_JSON));
        String message;
        if (response.getStatus() == 200) {
            message = "Successfully added students to the database";
        } else if (response.getStatus() == 400) {
            message = "Unable to add student to the database";
        } else {
            message = "Unexpected response: " + response.getStatus();
        }
        List<String> students = getStudentsUsernames();
        for(String student: students) {
            userService.addStudent(student);
        }
        return message;
    }
    public List<String> getStudentsUsernames() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("usernames");
        Invocation.Builder builder = target.request();
        Response response = builder.get();
        if (response.getStatus() == 200) {
            List<String> content = response.readEntity(new GenericType<List<String>>(){});
            System.out.println("Response Content: " + content);
            return content;
        }
        return null;
    }
}
