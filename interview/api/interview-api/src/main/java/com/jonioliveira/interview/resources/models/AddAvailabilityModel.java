package com.jonioliveira.interview.resources.models;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddAvailabilityModel {

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date startDate;

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date endDate;

    @NotNull
    private int interviewerId;

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getInterviewerId() {
        return interviewerId;
    }
}
