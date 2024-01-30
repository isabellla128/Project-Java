package com.example.laborator9.retriever;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentsRetriever {
    private final String LIBERTY_URL = "http://localhost:9080/students/api/students";
    public String getStudents() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL);
        Invocation.Builder builder = target.request();
        Response response = builder.get();
        if (response.getStatus() == 200) {
            String content = response.readEntity(String.class);
            System.out.println("Response Content: " + content);
            return content;
        }
        return "error";
    }
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
        return message;
    }
    public List<String> getStudentsUsernames() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("usernames");
        Invocation.Builder builder = target.request();
        Response response = builder.get();
        ArrayList<String>content=new ArrayList<>();
        if (response.getStatus() == 200) {
            content = response.readEntity(ArrayList.class);
            System.out.println("Response Content: " + content);
            return content;
        }
        return content;
    }
}
