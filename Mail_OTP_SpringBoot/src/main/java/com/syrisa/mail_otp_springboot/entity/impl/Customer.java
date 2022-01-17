package com.syrisa.mail_otp_springboot.entity.impl;

import com.syrisa.mail_otp_springboot.entity.Entity;
import lombok.Data;

@Data
public class Customer implements Entity {
    private Long customerID;
    private String customerName;
    private String customerLastName;
    private String customerEmail;
    private String customerPassword;
}
