package com.jonioliveira.interview.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddUserRequest {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("type")
    private int type;

    public AddUserRequest(String name, int type) {
        this.name = name;
        this.type = type;
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
