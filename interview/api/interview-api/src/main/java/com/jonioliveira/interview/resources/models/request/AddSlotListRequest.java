package com.jonioliveira.interview.resources.models.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(name="Add Slot Request Model", description="Model of request body to add a slot")
public class AddSlotListRequest {

    @NotNull
    @Min(1)
    @Schema(description="List of slots")
    private List<AddSlotRequest> list;

    public List<AddSlotRequest> getList() {
        return list;
    }

    public void setList(List<AddSlotRequest> list) {
        this.list = list;
    }
}
