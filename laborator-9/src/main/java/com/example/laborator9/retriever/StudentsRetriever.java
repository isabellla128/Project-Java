package com.example.laborator9.retriever;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class StudentsRetriever {
    private final String LIBERTY_URL = "http://localhost:9080/students/api/students";
    public String getStudents() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL);
        Invocation.Builder builder = target.request();
        // Make an HTTP GET request (you can use other HTTP methods like POST, PUT, etc.)
        Response response = builder.get();

        // Check if the request was successful (status code 200)
        if (response.getStatus() == 200) {
            // Get the response content as a String
            String content = response.readEntity(String.class);

            // Print the content
            System.out.println("Response Content: " + content);
            return content;
        }
        return "error";
    }
}
