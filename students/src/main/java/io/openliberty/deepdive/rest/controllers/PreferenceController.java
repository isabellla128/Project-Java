package io.openliberty.deepdive.rest.controllers;

import io.openliberty.deepdive.rest.entities.Preference;
import io.openliberty.deepdive.rest.models.PreferenceDTO;
import io.openliberty.deepdive.rest.repositories.PreferenceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Preference.class,
            responseDescription = "A list of preferences stored within the inventory.",
            responseCode = "200")
    @Operation(
            summary = "List contents.",
            description = "Returns the currently stored preference in the inventory.",
            operationId = "getAllPreferences")
    public List<PreferenceDTO> getAllPreferences() {
        return preferenceRepository.getPreferences();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponseSchema(value = Preference.class,
            responseDescription = "A list of preferences stored within the inventory.",
            responseCode = "200")
    @Operation(
            summary = "List contents.",
            description = "Returns the currently stored preference in the inventory.",
            operationId = "getAllPreferences")

    public List<PreferenceDTO> getAllPreferences(
        @Parameter(
            name = "username", in = ParameterIn.PATH,
            description = "The username of the student",
            required = true, example = "dana.petrea@gmail.com",
            schema = @Schema(type = SchemaType.STRING))
        @PathParam("username") String username) {
        return preferenceRepository.getPreferencesByUsername(username);
    }

    @POST
    @Path("/")
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