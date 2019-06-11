package com.jonioliveira.users.exception;

public class UserNotFoundException extends Exception {
    private String name;

    public UserNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
