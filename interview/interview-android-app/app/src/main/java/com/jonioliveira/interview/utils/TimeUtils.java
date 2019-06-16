package com.jonioliveira.interview.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static boolean dateIsSame(Date first, Date second){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK);
        return fmt.format(first).equals(fmt.format(second));
    }
}
