package com.jonioliveira.users.resource.model;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
