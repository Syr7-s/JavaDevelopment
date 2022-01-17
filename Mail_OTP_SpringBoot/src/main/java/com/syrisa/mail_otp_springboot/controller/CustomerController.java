package com.syrisa.mail_otp_springboot.controller;


import com.syrisa.mail_otp_springboot.entity.impl.Customer;
import com.syrisa.mail_otp_springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        try{
            return  ResponseEntity.ok(customerService.create(customer));
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }

    @GetMapping("/allCustomer")
    public List<Customer> getAllCustomer(){
        return customerService.getCustomers();
    }

    @GetMapping("/customer/{mail}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("mail") String mail){
        try {
            Customer customer = customerService.getCustomerByEmail(mail);
            if (customer!=null){
                return ResponseEntity.ok(customerService.getCustomerByEmail(mail));
            }
            throw new Exception("This mail not use by Customers");
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }
}
