package com.jonioliveira.interview.utils;

import com.jonioliveira.interview.exceptions.TimeErrorException;
import com.jonioliveira.interview.resources.SlotResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);

    public static boolean checkMinutesAndSeconds(Date date) throws TimeErrorException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int minutes = instance.get(Calendar.MINUTE);
        int seconds = instance.get(Calendar.SECOND);
        if (minutes == 0 && seconds == 0) {
            return true;
        }

        throw new TimeErrorException();
    }

    public static boolean checkDifference(Date startDate, Date endDate) throws TimeErrorException {
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        int diff = (int) (endDate.getTime() - startDate.getTime()) / MILLI_TO_HOUR;
        LOGGER.info("==== Diff: {}", diff);
        if (diff == 1) {
            return true;
        }
        throw new TimeErrorException();
    }
}
