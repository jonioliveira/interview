package com.jonioliveira.interview.resources;

import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.models.SlotStatus;
import com.jonioliveira.interview.resources.models.request.AddSlotRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateAndUserRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateRequest;
import com.jonioliveira.interview.resources.models.request.ScheduleInterviewRequest;
import com.jonioliveira.interview.resources.models.response.SlotResponse;
import com.jonioliveira.interview.services.SlotService;
import com.jonioliveira.interview.utils.Constants;
import com.jonioliveira.interview.utils.Mapper;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path(Constants.slotsResourcePath)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SlotResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotResource.class);

    @Inject
    SlotService service;

    @POST
    @Timed(name = "add_slot_list", unit = MetricUnits.MILLISECONDS)
    @Counted(name = "add_slot_list_count", monotonic = true)
    @Operation(summary = "Add a list of slots to system")
    @Tag(name = "Add")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response add(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = AddSlotRequest.class)))
                                @Valid ArrayList<AddSlotRequest> request){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: ADD SLOTS LIST | REQUEST");

            List<Slot> slotsList= service.addSlotList(request);

            LOGGER.info("[MONITORING] | METHOD: ADD SLOTS LIST | RESPONSE | Time: {}ms", System.currentTimeMillis()-startTime);

            return Response.ok(Mapper.toSlotResponse(slotsList)).status(201).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: ADD SLOTS LIST | {}", e.getCause(), e);
            throw new WebApplicationException("Add slot error", 500);
        }
    }

    @POST
    @Path("/date")
    @Timed(name = "get_slots_by_date", unit = MetricUnits.MILLISECONDS)
    @Counted(name = "get_slots_by_date_count", monotonic = true)
    @Operation(summary = "Get a list of slots from a day")
    @Tag(name = "Get")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByDate(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = GetSlotsByDateRequest.class)))
                                  @Valid GetSlotsByDateRequest request){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | REQUEST");

            List<Slot> slotsList= service.getSlotsByDate(request);

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | RESPONSE | Time: {}ms", System.currentTimeMillis()-startTime);

            return Response.ok(Mapper.toSlotResponse(slotsList)).status(200).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: GET SLOTS BY DATE | {}", e.getCause(), e);
            throw new WebApplicationException("Get slots by date " + e.getCause().getMessage(), 500);
        }

    }

    @POST
    @Path("/dateuser")
    @Timed(name = "get_slots_by_date_and_user", unit = MetricUnits.MILLISECONDS)
    @Counted(name = "get_slots_by_date_and_user_count", monotonic = true)
    @Operation(summary = "Get a list of slots from a day and user")
    @Tag(name = "Get")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByDateAndUser(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = GetSlotsByDateAndUserRequest.class)))
                                         @Valid GetSlotsByDateAndUserRequest request){
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | REQUEST");

            ArrayList<Slot> slotsList= (ArrayList<Slot>) service.getSlotsByDateAndUser(request);

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | RESPONSE | Time: {}ms", System.currentTimeMillis()-startTime);

            return Response.ok(Mapper.toSlotResponse(slotsList)).status(200).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: GET SLOTS BY DATE | {}", e.getCause(), e);
            throw new WebApplicationException("Get slots by date and user " + e.getCause().getMessage(), 500);
        }
    }

    @PUT
    @Path("/schedule")
    @Timed(name = "schedule_interview", unit = MetricUnits.MILLISECONDS)
    @Counted(name = "schedule_interview_count", monotonic = true)
    @Operation(summary = "Schedule an interview")
    @Tag(name = "Schedule")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slot of interview", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response scheduleInterview(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = ScheduleInterviewRequest.class)))
                                          @Valid ScheduleInterviewRequest request){
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: SCHEDULE INTERVIEW | REQUEST");

            Slot slot = service.updateSlotStatus(request.getSlotId(), request.getCandidateId(), SlotStatus.INTERVIEW);

            LOGGER.info("[MONITORING] | METHOD: SCHEDULE INTERVIEW | RESPONSE | Time: {}ms", System.currentTimeMillis()-startTime);

            return Response.ok(Mapper.toSlotResponse(slot)).status(200).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: SCHEDULE INTERVIEW | {}", e.getCause(), e);
            throw new WebApplicationException("Add slot error", 500);
        }
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }
    }
}
