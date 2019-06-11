package com.jonioliveira.users.exception;

public class UserTypeNotFoundException extends Exception {

    private int id;

    public UserTypeNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
