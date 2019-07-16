package com.jonioliveira.users.exception;

public class UserNotFoundException extends Exception {
    private String name;

    private int id;

    public UserNotFoundException(String name) {
        this.name = name;
    }

    public UserNotFoundException(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
