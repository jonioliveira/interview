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

import java.util.Arrays;
import java.util.List;

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

            if (request.getStartDate().after())
        }
        return null;
    }

    @Override
    public List<Slot> getSlotsByDate(GetSlotsByDateRequest request) throws SlotsNotFoundException {
        return Arrays.asList(new Slot());
    }

    @Override
    public List<Slot> getSlotsByDateAndUser(GetSlotsByDateAndUserRequest request) throws SlotsNotFoundException {
        return Arrays.asList(new Slot());
    }

    @Override
    public Slot updateSlotStatus(int slotId, int candidateId, SlotStatus status) {
        return new Slot();
    }

    @Override
    public Slot deleteSlot(int slotId) {
        return new Slot();
    }
}
