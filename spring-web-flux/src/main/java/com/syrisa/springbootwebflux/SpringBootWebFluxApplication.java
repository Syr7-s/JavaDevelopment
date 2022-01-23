package com.syrisa.springbootwebflux;

import com.syrisa.springbootwebflux.entity.Employee;
import com.syrisa.springbootwebflux.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootWebFluxApplication {

    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebFluxApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void appStart() {
        try{
            if (employeeRepository.count().block() == 0) {
                IntStream.range(0, 100)
                        .mapToObj(this::generate)
                        .map(employeeRepository::save)
                        .collect(Collectors.toList())
                        .forEach(item -> item.subscribe(System.out::println));
            }
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        }
    }

    public Employee generate(int i) {
        return Employee.builder()
                .name("Name : " + i)
                .lastName("LastName : " + i)
                .birthDate(LocalDate.now())
                .build();

    }
}
