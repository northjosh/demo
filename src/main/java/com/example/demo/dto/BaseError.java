package com.example.demo.dto;

import java.io.Serial;
import java.io.Serializable;

public class BaseError implements Serializable {

    @Serial
    private static final long serialVersionUID = -5L;

    private int errorCode;
    private  String errorMessage;
    private String url;


}
