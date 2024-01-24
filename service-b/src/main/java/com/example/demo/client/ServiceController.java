package com.example.demo.client;

import com.example.demo.services.DormitoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/client/service")
public class ServiceController {

    @Inject
    private DormitoryService dormitoryService;

    @GET
    @Path("/{parameter}")
    public String doSomething(@PathParam("parameter") String parameter) {
        return dormitoryService.getAll();
    }
}
