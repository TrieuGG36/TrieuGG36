package com.example.smartphones.api;


import com.example.smartphones.dto.request.customer.CustomerRequest;
import com.example.smartphones.entity.CustomerEntity;
import com.example.smartphones.service.ICustomer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@Slf4j
@RequiredArgsConstructor
public class AccountApi {

    private final ICustomer staffService;

    @GetMapping("/count_login_false")
    public ResponseEntity<String> getCountLoginFalse(@RequestParam("email") String email) {
        log.info("login check");
        System.out.println("hihihi");
        CustomerEntity staff = staffService.findByEmail(email);
        if (staff != null) {
            System.out.println(staff.getId());
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai tài khoản hoặc mật khẩu");
    }

    @GetMapping("/phone")
    public ResponseEntity<String> checkPhone(@RequestParam("phone") String phone) {
        log.info("login check");
        CustomerEntity staff = staffService.findByPhone(phone);
        if (staff != null) {
            System.out.println(staff.getId());
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai tài khoản hoặc mật khẩu");
    }


    @PostMapping("/register")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequest request) {
        log.info("Add customer start");
        String staff = staffService.addCustomer(request);
        if (staff.equals("ok")){
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
        return ResponseEntity.badRequest().body("false");
    }
}
