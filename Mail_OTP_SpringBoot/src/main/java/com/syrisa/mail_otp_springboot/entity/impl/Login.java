package com.syrisa.mail_otp_springboot.entity.impl;

import com.syrisa.mail_otp_springboot.entity.Entity;
import lombok.Data;

@Data
public class Login implements Entity {
    private String email;
    private String password;
}
