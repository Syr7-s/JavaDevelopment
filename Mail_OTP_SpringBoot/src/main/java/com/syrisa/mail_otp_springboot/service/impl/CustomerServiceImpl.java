package com.syrisa.mail_otp_springboot.service.impl;

import com.syrisa.mail_otp_springboot.entity.impl.Customer;
import com.syrisa.mail_otp_springboot.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Map<Long, Customer> customers = new HashMap<>();

    @Override
    public Customer create(Customer customer) {
        if (customers.containsKey(customer.getCustomerID())) {
            return customers.get(customer.getCustomerID());
        } else {
            customers.put(customer.getCustomerID(), customer);
            return customer;
        }
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        List<Customer> cus = customers.values().stream().filter(customer -> customer.getCustomerEmail().equals(email)).collect(Collectors.toList());
        if (cus.size() > 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "One more email have register on system.Email must unique");
        } else if (cus.size() == 0) {
            return null;
        }
        return cus.get(0);
    }
}
