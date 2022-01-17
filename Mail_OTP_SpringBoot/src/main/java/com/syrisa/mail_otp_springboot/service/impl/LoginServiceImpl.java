package com.syrisa.mail_otp_springboot.service.impl;

import com.syrisa.mail_otp_springboot.entity.impl.Customer;
import com.syrisa.mail_otp_springboot.entity.impl.Login;
import com.syrisa.mail_otp_springboot.entity.impl.TempEmail;
import com.syrisa.mail_otp_springboot.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final CustomerServiceImpl customerService;
    private final MailServiceImpl mailService;
    private final StoredEmailOTPServiceImpl storedEmailOTPService;

    @Override
    public String login(Login login) {
        try {
            Customer customer = customerService.getCustomerByEmail(login.getEmail());
            if (customer != null) {
                if (login.getPassword().equals(customer.getCustomerPassword())) {
                    mailService.generateOneTimePassword(new TempEmail(),customer);
                    return "Mail send successfully";
                }
                throw new Exception("Password incorrect");
            }
            throw new Exception("Customer not found");
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
    @Override
    public String loginWithTheOTPMessage(String otpMessage){
        try{
            return storedEmailOTPService.verifyOTP(otpMessage) ? "Login Successfully" : "Otp Message is invalid";
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }
}
