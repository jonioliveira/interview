package com.jonioliveira.interview.resources;

import com.jonioliveira.interview.exceptions.AvailabilityNotFoundException;
import com.jonioliveira.interview.models.Availability;
import com.jonioliveira.interview.resources.models.AddAvailabilityModel;
import com.jonioliveira.interview.resources.models.GetByDateModel;
import com.jonioliveira.interview.services.AvailabilityService;
import com.jonioliveira.interview.utils.Constants;
import com.jonioliveira.interview.utils.ErrorMapperUtils;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@ApplicationScoped
@Path(Constants.availabilityResourcePath)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tags({@Tag(name = "Availability")})
public class AvailabilityResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityResource.class);

    @Inject
    AvailabilityService service;

    @POST
    @Timed(name = "add_availability")
    @Operation(summary = "Add availability to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The availability", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Availability.class))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response add(
            @RequestBody(description = "AddAvailabilityModel Request object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AddAvailabilityModel.class)))
            @Valid AddAvailabilityModel request ){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: ADD AVAILABILITY | REQUEST");

            Availability availability = service.addAvailability(request);

            LOGGER.info("[MONITORING] | METHOD: ADD AVAILABILITY | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(availability).status(201).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: ADD AVAILABILITY | {}", e.getCause(), e);
            throw new WebApplicationException("Add availability error", 500);
        }
    }


    @GET
    @Timed(name = "get_all_availability")
    @Operation(summary = "Get all availability from system")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The availability list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Availability.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Availability not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getAll() {
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET ALL AVAILABILITY | REQUEST");

            List<Availability> availabilityList = service.getAllAvailability();

            LOGGER.info("[MONITORING] | METHOD: GET ALL AVAILABILITY | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(availabilityList).build();
        } catch (AvailabilityNotFoundException e) {
            LOGGER.error("METHOD: ADD USER | {}", e.getCause(), e);
            throw new WebApplicationException("Availability not found", 404);
        }
    }

    @GET
    @Timed(name = "get_by_interviewer_id")
    @Path("/{id}")
    @Operation(summary = "Get all availability of interviewer")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The availability list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Availability.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Availability not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByInterviewerId(
            @Parameter(description = "The interviewer Id",
                    required = true, content = @Content(schema = @Schema(implementation = Integer.class)))
            @NotNull int id ){
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET BY INTERVIEWER | REQUEST");

            List<Availability> availabilityList = service.getByInterviewerId(id);

            LOGGER.info("[MONITORING] | METHOD: GET BY INTERVIEWER | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(availabilityList).build();
        } catch (AvailabilityNotFoundException e) {
            LOGGER.error("METHOD: ADD USER | {}", e.getCause(), e);
            throw new WebApplicationException("Availability not found", 404);
        }
    }

    @GET
    @Timed(name = "get_by_date")
    @Path("/date")
    @Operation(summary = "Get all availability from a date")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The availability list", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Availability.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Availability not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByStartDate(
            @RequestBody(description = "GetByDateModel Request object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GetByDateModel.class)))
            @Valid GetByDateModel request){
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET BY START DATE | REQUEST");

            List<Availability> availabilityList = service.getByStartDate(request);

            LOGGER.info("[MONITORING] | METHOD: GET BY START DATE | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(availabilityList).build();
        } catch (AvailabilityNotFoundException e) {
            LOGGER.error("METHOD: ADD USER | {}", e.getCause(), e);
            throw new WebApplicationException("Availability not found", 404);
        }
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            return ErrorMapperUtils.toResponse(exception);
        }
    }
}
