package com.tukorea.turtleneck.backend.domain.member.domain;

public enum Sex {
    MALE("남자",1),
    FEMALE("여자",2);

    private final String desc;
    private final Integer code;

    Sex(String desc, Integer code){
        this.desc = desc;
        this.code = code;
    }
}
