package com.example.demo.utils;

import com.example.demo.dto.ApiResponse;
import com.example.demo.enums.ResponseMessages;

import java.io.Serializable;

public class Utils {

    public Utils () {}

    public static <T extends Serializable> ApiResponse<T> wrapInApiResponse (T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(ResponseMessages.STUDENT_NOT_FOUND.getCode());
        response.setMessage(ResponseMessages.STUDENT_NOT_FOUND.getMessage());
        response.setData(data);

        return response;
    }
}
