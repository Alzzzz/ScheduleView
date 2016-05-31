package com.alzzzz.schedule.provider;

import java.util.List;

/**
 * Created by gaohaoqing
 * Date : 16/5/19
 * Time : 14:06
 */
public class CalendarProvider {

    public static String viewKey(String timeSlot, String daySlot) {
        String formatTimeSlot = timeSlot.substring(0, 2);
        return formatTimeSlot + daySlot;
    }

    public static boolean judeIsShow(String viewString, List<String> dataList) {
        if (dataList == null || dataList.size() == 0) return false;
        return dataList.contains(viewString);
    }
}
