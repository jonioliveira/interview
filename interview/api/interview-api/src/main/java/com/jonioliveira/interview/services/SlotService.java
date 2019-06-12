package com.jonioliveira.interview.services;

import com.jonioliveira.interview.exceptions.FailAddSlotException;
import com.jonioliveira.interview.exceptions.SlotsNotFoundException;
import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.models.SlotStatus;
import com.jonioliveira.interview.repositories.SlotsRepository;
import com.jonioliveira.interview.resources.models.request.AddSlotRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateAndUserRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SlotService {

    @Inject
    SlotsRepository repository;

    @Transactional
    public List<Slot> addSlotList(List<AddSlotRequest> requests) throws FailAddSlotException {
        List<Slot> slotList = new ArrayList<>();
        for (AddSlotRequest request : requests) {
            Slot slot = new Slot(request.getStartDate(), request.getEndDate(), request.getInterviewerId(), SlotStatus.AVAILABLE.getValue());
            repository.persist(slot);
            if(slot.isPersistent()){
                slotList.add(slot);
            } else {
                throw new FailAddSlotException();
            }
        }
        return slotList;
    }

    public List<Slot> getSlotsByDate(GetSlotsByDateRequest request) throws SlotsNotFoundException {
        return Optional.ofNullable(repository.findByDate(request.getDate())).orElseThrow(SlotsNotFoundException::new);
    }

    public List<Slot> getSlotsByDateAndUser(GetSlotsByDateAndUserRequest request) throws SlotsNotFoundException {
        return Optional.ofNullable(repository.findByDateAndUser(request.getDate(), request.getUserId())).orElseThrow(SlotsNotFoundException::new);
    }

    @Transactional
    public Slot updateSlotStatus(long slotId, int candidateId, SlotStatus status) {
        Slot slot = repository.findById(slotId);
        slot.setCandidateId(candidateId);
        slot.setStatus(status);
        return slot;
    }
}
