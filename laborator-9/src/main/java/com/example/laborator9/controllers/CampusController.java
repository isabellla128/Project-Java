package com.example.laborator9.controllers;

import com.example.laborator9.models.DormitoriesNamesResponse;
import com.example.laborator9.models.Dormitory;
import com.example.laborator9.models.DormitoryRoomsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("lab9")
public class CampusController {
    private final String LIBERTY_URL = "http://localhost:9080/dormitories/api/service";

    @GET
    @Path("dormitories")
    public List<Dormitory> getDormitories() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("dormitories");
        Invocation.Builder builder = target.request();
        Response response = builder.get();

        if (response.getStatus() == 200) {
            List<Dormitory> content = response.readEntity(new GenericType<List<Dormitory>>(){});
            System.out.println("Response Content: " + content);
            return content;
        }
        return null;
    }


    @GET
    @Path("dormitories/names")
    public DormitoriesNamesResponse getDormitoriesNames() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("dormitories/names");
        Invocation.Builder builder = target.request();
        Response response = builder.get();

        if (response.getStatus() == 200) {
            DormitoriesNamesResponse content = response.readEntity(DormitoriesNamesResponse.class);
            System.out.println("Response Content: " + content);
            return content;
        }
        return null;
    }

    @GET
    @Path("dormitory/{name}/rooms")
    public List<String> getStudent(@PathParam("name") String name) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(LIBERTY_URL).path("dormitory").path(name).path("rooms");
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
