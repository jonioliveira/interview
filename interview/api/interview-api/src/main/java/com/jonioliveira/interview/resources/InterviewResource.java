package com.jonioliveira.interview.resources;

import com.jonioliveira.interview.exceptions.InterviewNotFoundException;
import com.jonioliveira.interview.models.Interview;
import com.jonioliveira.interview.resources.models.AddInterviewModel;
import com.jonioliveira.interview.resources.models.GetByDateModel;
import com.jonioliveira.interview.services.InterviewService;
import com.jonioliveira.interview.utils.Constants;
import com.jonioliveira.interview.utils.ErrorMapperUtils;
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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@ApplicationScoped
@Path(Constants.interviewResourcePath)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tags({@Tag(name = "Interview")})
public class InterviewResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewResource.class);

    @Inject
    InterviewService service;

    @POST
    @Timed(name = "add_interview")
    @Counted(name = "add_interview")
    @Operation(summary = "Add interview to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The interview", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Interview.class))),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response add(
            @RequestBody(description = "AddInterviewModel Request object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AddInterviewModel.class)))
            @Valid AddInterviewModel request ){

        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: ADD INTERVIEW | REQUEST");

            Interview interview = service.addInterview(request);

            LOGGER.info("[MONITORING] | METHOD: ADD INTERVIEW | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(interview).status(201).build();
        } catch (Exception e) {
            LOGGER.error("METHOD: ADD INTERVIEW | {}", e.getCause(), e);
            throw new WebApplicationException("Add interview error", 500);
        }
    }

    @GET
    @Timed(name = "get_interview_by_date")
    @Operation(summary = "Get interview from system by date")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The interview list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interview.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Interview Not Found Error"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getByStartDate(
            @RequestBody(description = "AddInterviewModel Request object",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GetByDateModel.class)))
            @Valid GetByDateModel request ){
        try {
            long startTime = System.currentTimeMillis();

            LOGGER.info("[MONITORING] | METHOD: GET INTERVIEW FROM DATE | REQUEST");

            List<Interview> interview = service.getInterviewsByDate(request);

            LOGGER.info("[MONITORING] | METHOD: GET INTERVIEW FROM DATE | RESPONSE | Time: {}", startTime-System.currentTimeMillis());

            return Response.ok(interview).build();
        } catch (InterviewNotFoundException e) {
            LOGGER.error("METHOD: ADD INTERVIEW | {}", e.getCause(), e);
            throw new WebApplicationException("Add interview error", 500);
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