package com.syrisa.mail_otp_springboot.controller;


import com.syrisa.mail_otp_springboot.entity.impl.Login;
import com.syrisa.mail_otp_springboot.entity.impl.TempEmail;
import com.syrisa.mail_otp_springboot.service.LoginService;
import com.syrisa.mail_otp_springboot.service.impl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        try{
            return new ResponseEntity<>(loginService.login(login), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verifyLogin")
    public ResponseEntity<String> verifyLogin(@RequestBody TempEmail tempEmail){
        try {
            return new ResponseEntity<>(loginService.loginWithTheOTPMessage(tempEmail.getTempEmailOTP()),HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
