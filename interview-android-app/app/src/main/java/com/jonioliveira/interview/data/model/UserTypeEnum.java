package com.jonioliveira.interview.data.model;

public enum  UserTypeEnum {
    INTERVIEWER(1),
    CANDIDATE(2);

    private final int value;

    UserTypeEnum(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static UserTypeEnum fromValue(int value){
        for(UserTypeEnum userTypeEnum: values()){
            if (userTypeEnum.value == value){
                return userTypeEnum;
            }
        }
        return null;
    }
}
