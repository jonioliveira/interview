package com.jonioliveira.interview.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleSlotRequest {
    @Expose
    @SerializedName("slotId")
    private int slotId;

    @Expose
    @SerializedName("candidateId")
    private int candidateId;

    public ScheduleSlotRequest(int slotId, int candidateId) {
        this.slotId = slotId;
        this.candidateId = candidateId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
}
