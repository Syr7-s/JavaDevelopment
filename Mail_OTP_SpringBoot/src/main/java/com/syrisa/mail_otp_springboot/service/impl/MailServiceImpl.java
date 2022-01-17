package com.syrisa.mail_otp_springboot.service.impl;

import com.syrisa.mail_otp_springboot.entity.impl.Customer;
import com.syrisa.mail_otp_springboot.entity.impl.TempEmail;
import com.syrisa.mail_otp_springboot.service.MailService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final StoredEmailOTPServiceImpl storedEmailOTP;

    @Override
    public void generateOneTimePassword(TempEmail tempEmail, Customer customer) throws UnsupportedEncodingException, MessagingException {
        String OTP = RandomString.make(8);
        String encodedOTP = passwordEncoder.encode(OTP);

        System.out.println(encodedOTP);
        tempEmail.setTempEmailOTP(OTP);
        sendOTPEmail(tempEmail,customer);
    }
    @Override
    public void sendOTPEmail(TempEmail tempEmail,Customer customer) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@mailtest.com", "Mail Support");
        helper.setTo(customer.getCustomerEmail());

        String subject = "Here's your One Time Password (OTP)";

        String content = "<p>Hello " + customer.getCustomerName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + tempEmail.getTempEmailOTP() + "</b></p>"
                + "<br>"
                + "<p>Note: :D </p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);

        storedEmailOTP.store(tempEmail);
    }
    @Override
    public void clearOTP(TempEmail tempEmail) {
      tempEmail.setTempEmailOTP(null);
    }

}
