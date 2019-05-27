package com.jonioliveira.interview;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jonioliveira.interview.utils.Constants;

@Path(Constants.interviewResourcePath)
public class InterviewResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "hello";
    }

    public void add(){

    }

    public void getAll(){

    }
}