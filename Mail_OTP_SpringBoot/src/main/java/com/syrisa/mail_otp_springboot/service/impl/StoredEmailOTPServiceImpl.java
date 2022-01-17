package com.syrisa.mail_otp_springboot.service.impl;

import com.syrisa.mail_otp_springboot.entity.impl.TempEmail;
import com.syrisa.mail_otp_springboot.service.StoredEmailOTPService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoredEmailOTPServiceImpl implements StoredEmailOTPService {

    private static final List<TempEmail> storedEmailOTPs = new ArrayList<>();

    @Override
    public void store(TempEmail tempEmail) {
        storedEmailOTPs.add(tempEmail);
    }

    @Override
    public Boolean verifyOTP(String otp) {
        return storedEmailOTPs.stream().anyMatch(tempEmail -> tempEmail.getTempEmailOTP().equals(otp));
    }
}
