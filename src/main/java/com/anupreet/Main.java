package com.anupreet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class Main {

    private final CustomerRepo customerRepo;

    public Main(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomer() {
        return customerRepo.findAll();
    }

    record NewCustomerReq(
            String name,
            String email,
            Integer age
    ) {

    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerReq request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepo.save(customer);
    }
}
