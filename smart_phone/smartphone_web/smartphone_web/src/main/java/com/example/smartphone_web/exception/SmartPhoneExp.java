package com.example.smartphone_web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class SmartPhoneExp extends RuntimeException{

    private final ErrorMessage errorMessage;

    public SmartPhoneExp(String errorCode){
        ErrorMessage message = ErrorMessageLoader.getMessage(errorCode);
        if(Objects.isNull(message)){
            message = new ErrorMessage();
            message.setVn(errorCode);
        }
        this.errorMessage = message;
    }

    public SmartPhoneExp(String errorCode, Object... args){
        this.errorMessage = ErrorMessageLoader.getMessage(errorCode);
    }
}
