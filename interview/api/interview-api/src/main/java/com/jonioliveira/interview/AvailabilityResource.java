package com.jonioliveira.interview;

import com.jonioliveira.interview.utils.Constants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(Constants.availabilityResourcePath)
public class AvailabilityResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "hello";
    }
}
