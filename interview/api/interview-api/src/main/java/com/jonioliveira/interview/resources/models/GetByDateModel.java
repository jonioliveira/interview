package com.jonioliveira.interview.resources.models;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class GetByDateModel {
    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }
}
