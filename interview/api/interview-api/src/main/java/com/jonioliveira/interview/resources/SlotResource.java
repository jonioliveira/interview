package com.jonioliveira.interview.resources;

import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.resources.models.request.AddSlotRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDate;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateAndUser;
import com.jonioliveira.interview.resources.models.response.SlotResponse;
import com.jonioliveira.interview.services.SlotService;
import com.jonioliveira.interview.utils.Constants;
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
import org.eclipse.microprofile.openapi.annotations.tags.Tags;
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
import java.util.List;

@RequestScoped
@Path(Constants.slotsResourcePath)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tags({@Tag(name = "Slot")})
public class SlotResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotResource.class);

    @Inject
    SlotService service;

    @POST
    @Timed(name = "add_slot")
    @Counted(name = "add_slot")
    @Operation(summary = "Add slot to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slot", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response addSingle(@RequestBody(description = "Request object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AddSlotRequest.class)))
            @Valid AddSlotRequest request ){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: ADD SLOT | REQUEST");

            Slot slot= service.addSlot(request);

            LOGGER.info("[MONITORING] | METHOD: ADD SLOT | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(slot).status(201).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: ADD SLOT | {}", e.getCause(), e);
            throw new WebApplicationException("Add slot error", 500);
        }
    }

    @POST
    @Timed(name = "add_slot_list")
    @Counted(name = "add_slot_list")
    @Operation(summary = "Add a list of slots to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response addList(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = AddSlotRequest.class, type = SchemaType.ARRAY)))
                                @Valid List<AddSlotRequest> request){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: ADD SLOTS LIST | REQUEST");

            List<Slot> slotsList= service.addSlotList(request);

            LOGGER.info("[MONITORING] | METHOD: ADD SLOTS LIST | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(slotsList).status(201).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: ADD SLOTS LIST | {}", e.getCause(), e);
            throw new WebApplicationException("Add slot error", 500);
        }
    }

    @POST
    @Timed(name = "get_slots_by_date")
    @Counted(name = "get_slots_by_date")
    @Operation(summary = "Get a list of slots from a day")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByDate(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = GetSlotsByDate.class, type = SchemaType.ARRAY)))
                                  @Valid GetSlotsByDate request){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | REQUEST");

            List<Slot> slotsList= service.getSlotsByDate(request);

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(slotsList).status(200).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: GET SLOTS BY DATE | {}", e.getCause(), e);
            throw new WebApplicationException("Add slot error", 500);
        }

    }

    @POST
    @Timed(name = "add_slot_list")
    @Counted(name = "add_slot_list")
    @Operation(summary = "Add a list of slots to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotResponse.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByDateAndUser(@RequestBody(description = "Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = GetSlotsByDateAndUser.class, type = SchemaType.ARRAY)))
                                         @Valid GetSlotsByDateAndUser request){
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | REQUEST");

            List<Slot> slotsList= service.getSlotsByDateAndUser(request);

            LOGGER.info("[MONITORING] | METHOD: GET SLOTS BY DATE | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(slotsList).status(200).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: GET SLOTS BY DATE | {}", e.getCause(), e);
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
