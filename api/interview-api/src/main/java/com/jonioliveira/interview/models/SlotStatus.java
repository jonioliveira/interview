package com.jonioliveira.interview.models;

public enum SlotStatus {
    FREE(0),
    AVAILABLE(1),
    INTERVIEW(2);

    private final int value;

    SlotStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SlotStatus fromValue(int i){
        for (SlotStatus slotStatus : values()) {
            if (slotStatus.value == i) {
                return slotStatus;
            }
        }
        return null;
    }
}
