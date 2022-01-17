package com.syrisa.mail_otp_springboot.service;

import com.syrisa.mail_otp_springboot.entity.impl.Customer;
import com.syrisa.mail_otp_springboot.entity.impl.TempEmail;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService extends Service<TempEmail> {

    void generateOneTimePassword(TempEmail tempEmail, Customer customer)   throws UnsupportedEncodingException, MessagingException;

    void sendOTPEmail(TempEmail tempEmail, Customer customer)   throws UnsupportedEncodingException, MessagingException;

    void clearOTP(TempEmail tempEmail);
}
