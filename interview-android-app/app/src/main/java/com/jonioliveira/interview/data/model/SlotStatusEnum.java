package com.jonioliveira.interview.data.model;

public enum SlotStatusEnum {
    FREE(0),
    AVAILABLE(1),
    INTERVIEW(2);

    private final int value;

    SlotStatusEnum(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static SlotStatusEnum fromValue(int value){
        for(SlotStatusEnum slotStatusEnum: values()){
            if (slotStatusEnum.value == value){
                return slotStatusEnum;
            }
        }
        return null;
    }
}