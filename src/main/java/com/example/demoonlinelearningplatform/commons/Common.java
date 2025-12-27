package com.example.demoonlinelearningplatform.commons;

import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class Common {

    public static void commonHandlerError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            throw new InvalidException("Xảy ra lỗi data input", errors);
        }
    }
}
