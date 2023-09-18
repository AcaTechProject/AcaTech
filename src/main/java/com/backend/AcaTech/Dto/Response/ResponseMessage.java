package com.backend.AcaTech.Dto.Response;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ResponseMessage<T> {
    private boolean success;
    private String message;
    private T data;

    public ResponseMessage(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

