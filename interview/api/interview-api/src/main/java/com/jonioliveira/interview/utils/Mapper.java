package com.jonioliveira.interview.utils;

import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.resources.models.response.SlotResponse;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static SlotResponse toSlotResponse(Slot slot){
        return new SlotResponse(slot.getId(), slot.getStartDate(), slot.getEndDate(), slot.getInterviewerId(), slot.getCandidateId(), slot.getStatus().getValue());
    }

    public static ArrayList<SlotResponse> toSlotResponse(List<Slot> slots){
        ArrayList<SlotResponse> slotList = new ArrayList<>();
        for (Slot slot : slots) {
            slotList.add(new SlotResponse(slot.getId(), slot.getStartDate(), slot.getEndDate(), slot.getInterviewerId(), slot.getCandidateId(), slot.getStatus().getValue()));
        }
        return slotList;
    }
}
