package com.jonioliveira.interview.services;

import com.jonioliveira.interview.models.User;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/user")
@Dependent
@RegisterRestClient
public interface UserService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    User getUserById(@PathParam("id") int id);

}
