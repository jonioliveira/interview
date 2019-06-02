package com.jonioliveira.interview.resources.models;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class GetByDateModel {
    @NotNull
    @JsonbDateFormat("yyyy-MM-ddThh:mm:ss")
    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }
}
