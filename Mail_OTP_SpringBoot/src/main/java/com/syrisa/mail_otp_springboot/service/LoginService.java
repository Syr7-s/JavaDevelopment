package com.syrisa.mail_otp_springboot.service;

import com.syrisa.mail_otp_springboot.entity.impl.Login;

public interface LoginService extends Service<Login> {

    String login(Login login);

    String loginWithTheOTPMessage(String otpMessage);
}
