package com.jonioliveira.users.resource;

import com.jonioliveira.users.domain.model.User;
import com.jonioliveira.users.exception.UserAlreadyExistsException;
import com.jonioliveira.users.exception.UserNotFoundException;
import com.jonioliveira.users.exception.UserTypeNotFoundException;
import com.jonioliveira.users.resource.model.request.LoginRequest;
import com.jonioliveira.users.resource.model.request.UserRequest;
import com.jonioliveira.users.resource.model.response.UserResponse;
import com.jonioliveira.users.service.UserService;
import com.jonioliveira.users.utils.Constants;
import com.jonioliveira.users.utils.Mapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path(Constants.userResourcePath)
@RequestScoped
@Produces("application/json")
@Consumes("application/json")
@Tags({@Tag(name = "API")})
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Inject
    UserService service;

    @POST
    @Operation(summary = "Add user to system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "User added"),
            @APIResponse(responseCode = "404", description = "UserType not found"),
            @APIResponse(responseCode = "409", description = "User already exists"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response addUser(@RequestBody(description = "User Request object",
                                        required = true,
                                        content = @Content(schema = @Schema(implementation = UserRequest.class)))
                            @Valid UserRequest request){
        try {
            long startTime = System.currentTimeMillis();
            User user = service.addUser(request.getName(), request.getType());
            if (user != null){
                LOGGER.info("[MONITOR] | ADD USER | TIME:{}ms | STATUS: OK", System.currentTimeMillis() - startTime);
                return Response.ok(Mapper.userToUserResponse(user)).status(201).build();
            } else {
                LOGGER.info("[MONITOR] | ADD USER | TIME:{}ms | STATUS: ERR", System.currentTimeMillis() - startTime);
                throw new WebApplicationException("Error creating user");
            }
        } catch (UserTypeNotFoundException e) {
            LOGGER.error("[MONITOR] | ADD USER | STATUS: ERROR | {}", e.getMessage());
            throw new WebApplicationException("UserType with id of " + e.getId() + " does not exist.", 404);
        } catch (UserAlreadyExistsException e) {
            LOGGER.error("[MONITOR] | ADD USER | STATUS: ERROR | {}", e.getMessage());
            throw new WebApplicationException("User with name " + e.getName() + " already exists", 409);
        }
    }

    @POST
    @Path("{id}")
    @Operation(summary = "Login user in system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @APIResponse(responseCode = "404", description = "User not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response getUserById(@Parameter long id){
        long startTime = System.currentTimeMillis();
        try {
            final User user = service.getUserById(id);
            LOGGER.info("[MONITOR] | GET USER | TIME:{}ms | STATUS: OK", System.currentTimeMillis() - startTime);
            return Response.ok(Mapper.userToUserResponse(user)).status(200).build();
        } catch (UserNotFoundException e) {
            LOGGER.error("[MONITOR] | GET USER | STATUS: ERROR | {}", e.getClass().getCanonicalName());
            throw new WebApplicationException("User with name of " + e.getName() + " does not exist.", 404);
        }
    }

    @POST
    @Path("login")
    @Operation(summary = "Login user in system")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "The user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @APIResponse(responseCode = "404", description = "User not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error")
    })
    public Response login(@RequestBody(description = "Login Request object",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginRequest.class))) @Valid LoginRequest request){
        long startTime = System.currentTimeMillis();
        try {
            final User user = service.login(request.getName());
            LOGGER.info("[MONITOR] | LOGIN | TIME:{}ms | STATUS: OK", System.currentTimeMillis() - startTime);
            return Response.ok(Mapper.userToUserResponse(user)).status(200).build();
        } catch (UserNotFoundException e) {
            LOGGER.error("[MONITOR] | LOGIN | STATUS: ERROR | {}", e.getClass().getCanonicalName());
            throw new WebApplicationException("User with name of " + e.getName() + " does not exist.", 404);
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