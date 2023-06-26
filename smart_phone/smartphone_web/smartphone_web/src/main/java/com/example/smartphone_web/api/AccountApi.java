package com.example.smartphone_web.api;

import com.example.smartphone_web.constant.ConstansErrorCode;
import com.example.smartphone_web.entity.StaffEntity;
import com.example.smartphone_web.exception.SmartPhoneExp;
import com.example.smartphone_web.service.impl.StaffDetailsServiceImpl;
import com.example.smartphone_web.service.impl.StaffServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AccountApi {


    private final StaffServiceImpl service;

    private String message;

    @GetMapping("/count_login_false")
    public ResponseEntity<String> getCountLoginFalse(@RequestParam("email") String email){
        StaffEntity staff = service.getByEmail(email);
        if (staff != null){
            if (staff.getStatus().equals("0")){
                message = String.valueOf(new SmartPhoneExp(ConstansErrorCode.LOGIN_ACCOUNT_BLOCKED).getErrorMessage().getVn());
                log.info(message);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
            }
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        message = String.valueOf(new SmartPhoneExp(ConstansErrorCode.LOGIN_EMAIL_NOT_EXITS).getErrorMessage().getVn());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
