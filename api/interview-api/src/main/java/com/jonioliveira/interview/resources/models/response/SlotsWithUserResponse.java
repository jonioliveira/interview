package com.jonioliveira.interview.resources.models.response;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

@Schema(name="Slot with user name Response Model", description="Model of response body that correspond to slots with user name")
public class SlotsWithUserResponse extends SlotResponse{

    @Schema(description="The id of candidate", example = "John")
    private String interviewerName;

    public SlotsWithUserResponse(int id, Date startDate, Date endDate, int interviewerId, int candidateId, int status, String interviewerName) {
        super(id, startDate, endDate, interviewerId, candidateId, status);
        this.interviewerName = interviewerName;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }
}
