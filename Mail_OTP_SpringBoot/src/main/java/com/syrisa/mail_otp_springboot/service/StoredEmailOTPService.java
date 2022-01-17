package com.syrisa.mail_otp_springboot.service;

import com.syrisa.mail_otp_springboot.entity.impl.TempEmail;

public interface StoredEmailOTPService extends Service<TempEmail>{
    void store(TempEmail tempEmail);

    Boolean verifyOTP(String otp);
}
