package com.jonioliveira.interview.utils;

import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.models.User;
import com.jonioliveira.interview.resources.models.response.CountResponse;
import com.jonioliveira.interview.resources.models.response.SlotResponse;
import com.jonioliveira.interview.resources.models.response.SlotsWithUserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public static CountResponse toCountResponse(int value){
        return new CountResponse(value);
    }

    public static ArrayList<SlotsWithUserResponse> toSlotWithUserResponse(List<Slot> slots, Map<Integer, User> userMap){
        ArrayList<SlotsWithUserResponse> slotList = new ArrayList<>();
        for (Slot slot : slots) {
            slotList.add(new SlotsWithUserResponse(slot.getId(), slot.getStartDate(), slot.getEndDate(), slot.getInterviewerId(), slot.getCandidateId(), slot.getStatus().getValue(), userMap.get(slot.getInterviewerId()).getName()));
        }
        return slotList;
    }

}
