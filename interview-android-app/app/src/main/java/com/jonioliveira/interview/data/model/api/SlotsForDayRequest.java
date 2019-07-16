package com.jonioliveira.interview.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SlotsForDayRequest {
    @Expose
    @SerializedName("date")
    private Date date;

    public SlotsForDayRequest(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
