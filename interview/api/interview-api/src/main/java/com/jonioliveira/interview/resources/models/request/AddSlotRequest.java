package com.jonioliveira.interview.resources.models.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Schema(name="Add Slot Request Model", description="Model of request body to add a slot")
public class AddSlotRequest {

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Schema(description = "The start date of slot", example = "2019-08-10 10:00:00")
    private Date startDate;

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Schema(description = "the end date of slot", example = "2019-08-10 11:00:00")
    private Date endDate;

    @NotNull
    @Schema(description = "id of interviewer", example = "1")
    private Integer interviewerId;

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
}
