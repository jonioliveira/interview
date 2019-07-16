package com.jonioliveira.interview.utils;

import javax.json.Json;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ErrorMapperUtils {
    public static Response toResponse(Exception exception) {
        int code = 500;
        if (exception instanceof WebApplicationException) {
            code = ((WebApplicationException) exception).getResponse().getStatus();
        }
        return Response.status(code)
                .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                .build();
    }
}
