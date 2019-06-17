package com.jonioliveira.interview.resources.models.response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Count Response Model", description = "Model of response body that correspond to number of slots")
public class CountResponse{
    @Schema(description="The number of slots", example = "1")
    private int value;

    public CountResponse(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}