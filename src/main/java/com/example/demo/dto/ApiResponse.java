package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ApiResponse<T extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -3456L;


    private int code;

    private String message;

    private T data;

    private BaseError error;



}
