package com.syrisa.mail_otp_springboot.service;

import com.syrisa.mail_otp_springboot.entity.impl.Customer;

import java.util.List;

public interface CustomerService extends Service<Customer>{

    Customer create(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerByEmail(String email);

}
