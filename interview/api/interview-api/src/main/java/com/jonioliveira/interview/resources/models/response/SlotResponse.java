package com.jonioliveira.interview.resources.models.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

@Schema(name="Slot Response Model", description="Model of response body that correspond to slots")
public class SlotResponse {

    @Schema(description="The id of slot")
    private Long id;

    @Schema(description="The end date of slot")
    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @Schema(description="The end date of slot")
    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @Schema(description="The id of interviewer")
    private int interviewerId;

    @Schema(description="The id of candidate")
    private int candidateId;

    @Schema(description="The status of slot")
    private int status;

    public SlotResponse(Long id, Date startDate, Date endDate, int interviewerId, int candidateId, int status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
        this.candidateId = candidateId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
