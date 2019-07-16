package com.jonioliveira.interview;

import com.jonioliveira.interview.exceptions.FailAddSlotException;
import com.jonioliveira.interview.exceptions.SlotsNotFoundException;
import com.jonioliveira.interview.exceptions.TimeErrorException;
import com.jonioliveira.interview.models.Slot;
import com.jonioliveira.interview.models.SlotStatus;
import com.jonioliveira.interview.resources.models.request.AddSlotRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateAndUserRequest;
import com.jonioliveira.interview.resources.models.request.GetSlotsByDateRequest;
import com.jonioliveira.interview.services.SlotService;
import io.quarkus.test.Mock;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.jonioliveira.interview.utils.TimeUtils.checkDifference;
import static com.jonioliveira.interview.utils.TimeUtils.checkMinutesAndSeconds;

@Mock
public class MockSlotService extends SlotService {

    @Override
    public List<Slot> addSlotList(List<AddSlotRequest> requests) throws FailAddSlotException, TimeErrorException {
        for (AddSlotRequest request : requests) {
            checkMinutesAndSeconds(request.getStartDate());
            checkMinutesAndSeconds(request.getEndDate());
            checkDifference(request.getStartDate(), request.getEndDate());

            if (request.getInterviewerId() != 1){
                throw new FailAddSlotException();
            }

            return Collections.singletonList(new Slot(request.getStartDate(), request.getEndDate(), request.getInterviewerId(), SlotStatus.AVAILABLE.getValue()));

        }
        throw new FailAddSlotException();
    }

    @Override
    public List<Slot> getSlotsByDate(GetSlotsByDateRequest request) throws SlotsNotFoundException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);

        if(format.format(request.getDate()).equals("2019-12-05 10:00:00")){
            throw new SlotsNotFoundException();
        }

        Calendar instance = Calendar.getInstance();
        instance.setTime(request.getDate());
        instance.add(Calendar.HOUR_OF_DAY, 1);
        return Arrays.asList(new Slot(request.getDate(), instance.getTime(), 1, SlotStatus.AVAILABLE.getValue()));
    }

    @Override
    public List<Slot> getSlotsByDateAndUser(GetSlotsByDateAndUserRequest request) throws SlotsNotFoundException {
        if(request.getUserId() == 2){
            throw new SlotsNotFoundException();
        }

        Calendar instance = Calendar.getInstance();
        instance.setTime(request.getDate());
        instance.add(Calendar.HOUR_OF_DAY, 1);
        return Arrays.asList(new Slot(request.getDate(), instance.getTime(), request.getUserId(), SlotStatus.AVAILABLE.getValue()));
    }

    @Override
    public Slot updateSlotStatus(int slotId, int candidateId, SlotStatus status) throws SlotsNotFoundException {
        if(slotId != 1){
            throw new SlotsNotFoundException();
        }
        Slot slot = new Slot();
        slot.setCandidateId(candidateId);
        slot.setStatus(status);
        return slot;
    }

    @Override
    public Slot deleteSlot(int slotId) throws SlotsNotFoundException {

        if(slotId != 1){
            throw new SlotsNotFoundException();
        }
        return new Slot();
    }
}
