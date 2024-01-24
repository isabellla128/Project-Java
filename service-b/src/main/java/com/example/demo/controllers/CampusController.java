package com.example.demo.controllers;

import com.example.demo.services.DormitoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/client/campus")
public class CampusController {

    @Inject
    private DormitoryService dormitoryService;

    @GET
    @Path("/{parameter}")
    public String doSomething(@PathParam("parameter") String parameter) {
        return dormitoryService.getAll();
    }
}