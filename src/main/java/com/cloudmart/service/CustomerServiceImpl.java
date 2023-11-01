package com.cloudmart.service;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Customer;
import com.cloudmart.repository.CustomerRepository;
import com.cloudmart.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Override
    public Response createCustomer(Customer customer) {
        Response response = new Response();
        try {
            // check if customer already present
            Optional<Customer> existingCustomer = customerRepository.findFirstByNameAndContactNumber(customer.getName(), customer.getContactNumber());
            // if not present
            if (existingCustomer.isEmpty()) {
                // save customer
                Customer newCustomer = customerRepository.save(customer);
                response.setResponseData(newCustomer);
                response.setSuccess(true);
            }
            // else already present error message
            else {
                response.getErrorMessages().add("Customer already exist");
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Customer not created");
            log.error("Error in createCustomer {}", e);
        }
        return response;
    }

    @Transactional
    @Override
    public Response updateCustomer(Customer customer) {
        Response response = new Response();
        try {
            // check if customer is present
            Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
            // if present
            if (existingCustomer.isPresent()) {
                // then update
                Customer newCustomer = customerRepository.save(customer);
                response.setResponseData(newCustomer);
                response.setSuccess(true);
            }
            // else error message customer not found
            else {
                response.getErrorMessages().add("Customer not found");
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Customer not updated");
            log.error("Error in updateCustomer {}", e);
        }
        return response;
    }

    @Override
    public Response getCustomerById(int customerId) {
        Response response = new Response();
        try {
            // find customer by id
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            // if present than send as response
            if (existingCustomer.isPresent()) {
                response.setResponseData(existingCustomer.get());
                response.setSuccess(true);
            }
            // else error message
            else {
                response.getErrorMessages().add("Customer not found by Id :" + customerId);
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Customer not found by Id :" + customerId);
            log.error("Error in getCustomerById {}", e);
        }
        return response;
    }

    @Override
    public Response getAllCustomer() {
        Response response = new Response();
        try {
            // find all customers
            List<Customer> customers = customerRepository.findAll();
            // if customers are present tha return them as response
            if (customers.size() > 0) {
                response.setResponseData(customers);
                response.setSuccess(true);
            }
            // else error
            else {
                response.getErrorMessages().add("No customers to show.");
            }
        } catch (Exception e) {
            response.getErrorMessages().add("No customers to show.");
            log.error("Error in getAllCustomers {}", e);
        }
        return response;
    }

    @Transactional
    @Override
    public Response deleteCustomerById(int customerId) {
        Response response = new Response();
        try {
            // find the customer to delete
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            // if customer found than delete
            if (existingCustomer.isPresent()) {
                customerRepository.deleteById(customerId);
                response.setResponseData("Customer deleted successfully");
                response.setSuccess(true);
            }
            // else error
            else {
                response.getErrorMessages().add("Customer not found by Id :" + customerId);
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Customer not deleted");
            log.error("Error in deleteCustomerById {}", e);
        }
        return response;
    }
}
