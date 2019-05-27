package com.jonioliveira.users.resource;

import com.jonioliveira.users.domain.models.User;
import com.jonioliveira.users.exceptions.UserNotFoundException;
import com.jonioliveira.users.exceptions.UserTypeNotFoundException;
import com.jonioliveira.users.resource.model.LoginRequest;
import com.jonioliveira.users.resource.model.UserRequest;
import com.jonioliveira.users.services.MigrationService;
import com.jonioliveira.users.services.UserService;
import com.jonioliveira.users.utils.Constants;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path(Constants.userResourcePath)
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Inject
    UserService service;

    @POST
    @Operation(summary = "Add user to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "User not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response addUser(@RequestBody(description = "User Request object",
                                        required = true,
                                        content = @Content(schema = @Schema(implementation = UserRequest.class))) @Valid UserRequest request){
        try {
            User user = service.addUser(request.getName(), request.getType());
            return Response.ok(user).status(201).build();
        } catch (UserTypeNotFoundException e) {
            throw new WebApplicationException("UserType with id of " + e.getId() + " does not exist.", 404);
        }
    }

    @POST
    @Path("login")
    @Operation(summary = "Login user in system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "User not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response login(@RequestBody(description = "Login Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginRequest.class))) @Valid LoginRequest request){
        try {
            User user = service.login(request.getName());
            return Response.ok(user.getType().getId()).status(200).build();
        } catch (UserNotFoundException e) {
            throw new WebApplicationException("UserType with name of " + e.getName() + " does not exist.", 404);
        }
    }

/*    @Provider
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

    }*/
}