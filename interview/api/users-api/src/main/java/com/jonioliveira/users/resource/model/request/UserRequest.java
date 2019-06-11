package com.jonioliveira.users.resource.model.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(name="User Request Model", description="Model of request body to add users")
public class UserRequest {

    @NotNull
    @Schema(description = "name of user", example = "jhon")
    private String name;

    @NotNull
    @Schema(description = "The type of the user", enumeration = "1,2", example = "1")
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
