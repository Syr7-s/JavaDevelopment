package com.syrisa.mail_otp_springboot.entity.impl;

import com.syrisa.mail_otp_springboot.entity.Entity;
import lombok.Data;

@Data
public class TempEmail implements Entity {
    private String tempEmailOTP;
}
