package com.jonioliveira.users.utils;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database connection health check");
        try {
            return responseBuilder.up().build();
        } catch (IllegalStateException e) {
            // cannot access the database
            return responseBuilder.down()
                    .withData("error", e.getMessage()).build();
        }
    }
}
