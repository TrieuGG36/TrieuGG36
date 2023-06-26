package com.example.smartphone_web.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String vn;
    private String en;
    private String code;
}
