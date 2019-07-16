package com.jonioliveira.interview.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AddSlotRequest {

    @Expose
    @SerializedName("startDate")
    private Date startDate;

    @Expose
    @SerializedName("endDate")
    private Date endDate;

    @Expose
    @SerializedName("interviewerId")
    private int interviewerId;

    public AddSlotRequest(Date startDate, Date endDate, int interviewerId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }
}
