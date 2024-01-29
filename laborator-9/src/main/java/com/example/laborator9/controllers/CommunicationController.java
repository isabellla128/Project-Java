package com.example.laborator9.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Random;

@Path("lab9")
public class CommunicationController {
    private InputStream inputStream;

    {
        try {
            inputStream = Files.newInputStream(java.nio.file.Path.of("C:\\Users\\d.petrea\\OneDrive - Viamedici Software GmbH\\Dokumente\\GitHub\\Project-Java\\laborator-9\\students.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String LIBERTY_URL = "http://localhost:9080/students/api/students";

    @GET
    @Path("celsius")
    public String getTemperature() {
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
    @Path("celsius")
    public String getTemperature2() {
        InputStream jsonfile = this.inputStream;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL);
        System.out.println("daaa");
        Response response = target.request().post(Entity.entity(jsonfile, MediaType.APPLICATION_OCTET_STREAM));
        System.out.println("nuuuu");
//        Invocation.Builder builder = target.request();
//        // Make an HTTP GET request (you can use other HTTP methods like POST, PUT, etc.)
//        System.out.println("daaa");
//        Response response = builder.post(Entity.json(jsonfile));
//        System.out.println("nuuuu");
//        System.out.println(response.readEntity(String.class));
//        System.out.println(response.getStatus());
//        // Check if the request was successful (status code 200)
        if (response.getStatus() == 204) {
            // Get the response content as a String
            String content = response.readEntity(String.class);

            // Print the content
            System.out.println("Response Content: " + content);
            return content;
        }
        return "error";
    }
}