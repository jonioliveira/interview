package com.jonioliveira.users.resource.model;

import javax.validation.constraints.NotNull;

public class UserRequest {

    @NotNull
    private String name;

    @NotNull
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
