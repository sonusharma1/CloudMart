package com.cloudmart.controller;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Customer;
import com.cloudmart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("createCustomer")
    public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) {
        Response response = customerService.createCustomer(customer);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateCustomer")
    public ResponseEntity<Response> updateCustomer(@RequestBody Customer customer) {
        Response response = customerService.updateCustomer(customer);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("getCustomerById/{id}")
    public ResponseEntity<Response> getCustomerById(@PathVariable int id) {
        Response response = customerService.getCustomerById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("getAllCustomers")
    public ResponseEntity<Response> getAllCustomer() {
        Response response = customerService.getAllCustomer();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteCustomerById/{id}")
    public ResponseEntity<Response> deleteCustomerById(@PathVariable int id) {
        Response response = customerService.deleteCustomerById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
