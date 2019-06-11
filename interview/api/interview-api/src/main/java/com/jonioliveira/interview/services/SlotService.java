package com.jonioliveira.interview.services;

import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.repositories.SlotsRepository;
import com.jonioliveira.interview.resources.models.request.AddSlotRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDate;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateAndUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class SlotService {

    @Inject
    SlotsRepository repository;

    public Slot addSlot(AddSlotRequest request) {
        return null;
    }

    public List<Slot> addSlotList(List<AddSlotRequest> request) {
        return null;
    }

    public List<Slot> getSlotsByDate(GetSlotsByDate request) {
        return null;
    }

    public List<Slot> getSlotsByDateAndUser(GetSlotsByDateAndUser request) {
        return null;
    }
}
