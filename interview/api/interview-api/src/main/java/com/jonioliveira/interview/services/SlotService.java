package com.jonioliveira.interview.services;

import com.jonioliveira.interview.exceptions.FailAddSlotException;
import com.jonioliveira.interview.exceptions.SlotsNotFoundException;
import com.jonioliveira.interview.exceptions.TimeErrorException;
import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.models.SlotStatus;
import com.jonioliveira.interview.repositories.SlotsRepository;
import com.jonioliveira.interview.resources.models.request.AddSlotRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateAndUserRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateRequest;
import com.jonioliveira.interview.utils.TimeUtils;
import com.jonioliveira.interview.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.jonioliveira.interview.utils.TimeUtils.*;
import static com.jonioliveira.interview.utils.TimeUtils.checkMinutesAndSeconds;

@ApplicationScoped
public class SlotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotService.class);

    @Inject
    SlotsRepository repository;

    @Transactional
    public List<Slot> addSlotList(List<AddSlotRequest> requests) throws FailAddSlotException, TimeErrorException {
        List<Slot> slotList = new ArrayList<>();
        for (AddSlotRequest request : requests) {
            checkMinutesAndSeconds(request.getStartDate());
            checkMinutesAndSeconds(request.getEndDate());
            checkDifference(request.getStartDate(), request.getEndDate());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            if (repository.findByStartDateAndEndDateByUser(format.format(request.getStartDate()), format.format(request.getEndDate()), request.getInterviewerId()) != 0 ){
                throw new FailAddSlotException();
            }

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
    public Slot updateSlotStatus(int slotId, int candidateId, SlotStatus status) {
        Slot slot = repository.findById(slotId);
        slot.setCandidateId(candidateId);
        slot.setStatus(status);
        return slot;
    }

    @Transactional
    public Slot deleteSlot(int slotId){
        Slot slot = repository.findById(slotId);
        slot.delete();
        return slot;
    }

    public int countSlotsForDate(GetSlotsByDateRequest request){
        return Utils.safeLongToInt(repository.countByDate(request.getDate()));
    }
}
