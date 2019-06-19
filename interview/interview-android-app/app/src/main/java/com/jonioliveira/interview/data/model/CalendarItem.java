package com.jonioliveira.interview.data.model;

import java.util.Date;

public class CalendarItem {

    private int id;

    private boolean isSelected;

    private SlotStatusEnum status;

    private Date startDate;

    private Date endDate;

    private int slotId;

    private String interviewerName;

    public CalendarItem(int id, Date startDate, Date endDate) {
        this.id = id;
        this.isSelected = false;
        this.status = SlotStatusEnum.FREE;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CalendarItem(int id, int status, Date startDate, Date endDate, int slotId) {
        this.id = id;
        this.isSelected = false;
        this.status = SlotStatusEnum.fromValue(status);
        this.startDate = startDate;
        this.endDate = endDate;
        this.slotId = slotId;
    }

    public CalendarItem(int id, int status, Date startDate, Date endDate, int slotId, String interviewerName) {
        this.id = id;
        this.isSelected = false;
        this.status = SlotStatusEnum.fromValue(status);
        this.startDate = startDate;
        this.endDate = endDate;
        this.slotId = slotId;
        this.interviewerName = interviewerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SlotStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SlotStatusEnum status) {
        this.status = status;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }
}
