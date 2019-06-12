package com.jonioliveira.interview.resources.models.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Schema(name="Get Slots by date Request Model", description="Model of request body to get a list of slots of a certain day")
public class GetSlotsByDate {

    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Schema(description = "The date of slot", example = "2019-08-10 10:00:00")
    private Date date;

    public GetSlotsByDate(@NotNull Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
