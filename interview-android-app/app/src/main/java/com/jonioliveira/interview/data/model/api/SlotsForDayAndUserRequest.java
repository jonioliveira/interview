package com.jonioliveira.interview.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SlotsForDayAndUserRequest {
    @Expose
    @SerializedName("date")
    private Date date;

    @Expose
    @SerializedName("userId")
    private int userId;

    public SlotsForDayAndUserRequest(Date date, int userId) {
        this.date = date;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
