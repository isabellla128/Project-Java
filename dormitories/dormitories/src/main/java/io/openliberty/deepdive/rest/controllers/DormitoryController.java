package io.openliberty.deepdive.rest.controllers;


import io.openliberty.deepdive.rest.models.DormitoryDTO;
import io.openliberty.deepdive.rest.repositories.DormitoryRepository;
import io.openliberty.deepdive.rest.response.DormitoriesNamesResponse;
import io.openliberty.deepdive.rest.response.DormitoryRoomsResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/service/")
public class DormitoryController {

    @Inject
    private DormitoryRepository dormitoryRepository;

    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHello() {
        return "hello";
    }
    @GET
    @Path("/dormitories")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DormitoryDTO> getDormitories() {
        return dormitoryRepository.getDormitories();
    }

    @GET
    @Path("/dormitories/names")
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> getDormitoriesNames() {
        return dormitoryRepository.getDormitoriesNames();
    }

    @GET
    @Path("/dormitory/{name}/rooms")
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> getDormitoryRooms(@PathParam("name") String dormitoryName) {
        return dormitoryRepository.getDormitoryRooms(dormitoryName);
    }
}