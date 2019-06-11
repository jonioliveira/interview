package com.jonioliveira.users.exception;

public class UserAlreadyExistsException extends Exception {
    private String name;

    public UserAlreadyExistsException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
