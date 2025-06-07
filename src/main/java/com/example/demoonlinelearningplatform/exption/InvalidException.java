package com.example.demoonlinelearningplatform.exption;

import lombok.Getter;

import java.util.Map;

@Getter
public class InvalidException extends RuntimeException {

    private Map<String, String> items;

    public InvalidException(String msg) {
        super(msg);
    }

    public InvalidException(String msg, Map<String, String> fieldError) {
        super(msg);
        items = fieldError;
    }
}
