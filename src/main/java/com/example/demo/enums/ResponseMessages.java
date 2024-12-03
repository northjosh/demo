package com.example.demo.enums;

import lombok.Getter;
import org.aspectj.bridge.IMessage;

public enum ResponseMessages {

    STUDENT_NOT_FOUND(1000, "Student Not found!"),
    STUDENT_ALREADY_EXISTS(1001, "Student Already exists.");


    @Getter
    private final int code;
    @Getter
    private final String  message;

    ResponseMessages(int code, String message){
        this.code = code;
        this.message = message;
    }

//    public String getMessage() { return  this.message; }


}
