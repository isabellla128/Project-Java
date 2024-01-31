package com.example.demo.controllers;

import com.example.demo.models.DormitoryDTO;
import com.example.demo.repositories.DormitoryRepository;
import com.example.demo.response.DormitoriesNamesResponse;
import com.example.demo.response.DormitoryRoomsResponse;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/client/service")
public class DormitoryController {

    @Inject
    private DormitoryRepository dormitoryRepository;

    @GET
    @Path("/dormitories")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DormitoryDTO> getDormitories() {
        return dormitoryRepository.getDormitories();
    }

    @GET
    @Path("/dormitories/names")
    @Produces({MediaType.APPLICATION_JSON})
    public DormitoriesNamesResponse getDormitoriesNames() {
        return dormitoryRepository.getDormitoriesNames();
    }

    @GET
    @Path("/dormitory/{name}/rooms")
    @Produces({MediaType.APPLICATION_JSON})
    public DormitoryRoomsResponse getDormitoryRooms(@PathParam("name") String dormitoryName) {
        return dormitoryRepository.getDormitoryRooms(dormitoryName);
    }
}