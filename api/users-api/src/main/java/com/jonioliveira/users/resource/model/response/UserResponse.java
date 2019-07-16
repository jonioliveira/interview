package com.jonioliveira.users.resource.model.response;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name="User Response Model", description="Model of response body when login users")
public class UserResponse {
    @Schema(description = "Id of user", example = "1", type = SchemaType.NUMBER)
    private int id;
    @Schema(description = "Name of user", example = "jhon", type = SchemaType.STRING)
    private String name;
    @Schema(description = "Type of user", example = "1", type = SchemaType.NUMBER)
    private int type;

    public UserResponse(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
