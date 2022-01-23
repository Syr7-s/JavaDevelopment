package com.syrisa.springbootwebflux.controller;


import com.syrisa.springbootwebflux.entity.Employee;
import com.syrisa.springbootwebflux.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable("id") String id){
        return employeeRepository.findById(id);
    }

    @GetMapping("/employees")
    public Flux<Employee> getAll(){
        return employeeRepository.findAll();
    }
}
