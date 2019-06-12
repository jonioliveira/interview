package com.jonioliveira.interview.resources.models.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Schema(name="Get Slots by date and user Request Model", description="Model of request body to get a list of slots of a certain day and user")
public class GetSlotsByDateAndUserRequest {

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Schema(description = "The date of slot", example = "2019-08-10 10:00:00")
    private Date date;

    @NotNull
    @Schema(description = "id of user", example = "1")
    private int userId;

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
