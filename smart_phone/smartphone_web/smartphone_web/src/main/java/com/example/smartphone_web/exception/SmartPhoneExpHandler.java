package com.example.smartphone_web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SmartPhoneExpHandler {

    @ExceptionHandler(SmartPhoneExp.class)
    @ResponseBody
    ResponseEntity<?> smartPhoneNotFound(SmartPhoneExp e){
        return new ResponseEntity(e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
