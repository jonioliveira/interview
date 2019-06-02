package com.jonioliveira.interview.resources.models;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddInterviewModel {

    @NotNull
    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @NotNull
    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @NotNull
    private int interviewerId;

    @NotNull
    private int candidateId;

    @NotNull
    private int availabilityId;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }
}
