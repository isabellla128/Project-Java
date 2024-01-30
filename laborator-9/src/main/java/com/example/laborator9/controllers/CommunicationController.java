package com.example.laborator9.controllers;

import org.primefaces.shaded.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Random;

@Path("lab9")
public class CommunicationController {
    private final String LIBERTY_URL = "http://localhost:9080/students/api/students";

    @GET
    @Path("students")
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

    @POST
    @Path("students")
    public String postStudents() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL);
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        InputStream yourJsonPayloadInputStream = getJsonPayloadInputStream();
        Response response = invocationBuilder.post(Entity.entity(yourJsonPayloadInputStream, MediaType.APPLICATION_JSON));
        String message;
        if (response.getStatus() == 200) {
            message="Successfully added students to the database";
        } else if (response.getStatus() == 400) {
            message="Unable to add student to the database";
        } else {
            message="Unexpected response: " + response.getStatus();
        }
        return message;
    }
    private static InputStream getJsonPayloadInputStream() throws IOException {
        return new FileInputStream("C:\\Users\\d.petrea\\OneDrive - Viamedici Software GmbH\\Dokumente\\GitHub\\Project-Java\\laborator-9\\students.json");
    }

    @GET
    @Path("students/name")
    public String getStudent() {
        String name = "Petrea Daniela";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path(name);
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

    @GET
    @Path("students/email")
    public String getStudentByEmail() {
        String email = "dana.petrea@gmail.com";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path(email);
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