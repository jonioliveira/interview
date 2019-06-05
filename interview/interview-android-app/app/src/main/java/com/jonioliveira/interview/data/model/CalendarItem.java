package com.jonioliveira.interview.data.model;

public class CalendarItem {

    private int id;

    private String hour;

    private boolean isSelected;

    private boolean isAvailable;

    public CalendarItem(int id, String hour, boolean isSelected, boolean isAvailable) {
        this.id = id;
        this.hour = hour;
        this.isSelected = isSelected;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
