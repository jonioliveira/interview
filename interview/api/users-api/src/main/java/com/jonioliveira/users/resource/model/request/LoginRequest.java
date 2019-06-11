package com.jonioliveira.users.resource.model.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(name="Login Request Model", description="Model of request body to login users")
public class LoginRequest {
    @NotNull
    @Schema(description = "Name of user", example = "jhon")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
