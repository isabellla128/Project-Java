package io.openliberty.deepdive.rest.controllers;

import io.openliberty.deepdive.rest.entities.Preference;
import io.openliberty.deepdive.rest.entities.Student;
import io.openliberty.deepdive.rest.models.PreferenceDTO;
import io.openliberty.deepdive.rest.repositories.PreferenceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@ApplicationScoped
@Path("/preferences/")
public class PreferenceController {
    @Inject
    PreferenceRepository preferenceRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Preference.class,
            responseDescription = "A list of preferences stored within the inventory.",
            responseCode = "200")
    @Operation(
            summary = "List contents.",
            description = "Returns the currently stored preference in the inventory.",
            operationId = "listContents")
    public List<Preference> listContents() {
        return preferenceRepository.getPreferences();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200",
                    description = "Successfully added preferences to database"),
            @APIResponse(responseCode = "400",
                    description = "Unable to add preferences to database")
    })
    @Operation(
            summary = "Add preferences",
            description = "Add preferences in database",
            operationId = "addPreferences"
    )
    public Response addPreferences(PreferenceDTO preferenceDTO) throws Exception {
        preferenceRepository.addPreference(preferenceDTO);
        return success("Preferences were added in database.");
    }

    private Response success(String message) {
        return Response.ok("{ \"ok\" : \"" + message + "\" }").build();
    }

    private Response fail(String message) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{ \"error\" : \"" + message + "\" }")
                .build();
    }
}