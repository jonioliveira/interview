package com.jonioliveira.users.resource.model.response;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name="User Response Model", description="Model of response body when login users")
public class UserResponse {
    @Schema(description = "Id of user", example = "1", type = SchemaType.NUMBER)
    private long id;
    @Schema(description = "Name of user", example = "jhon", type = SchemaType.STRING)
    private String name;
    @Schema(description = "Type of user", example = "1", type = SchemaType.NUMBER)
    private long type;

    public UserResponse(long id, String name, long type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
}
