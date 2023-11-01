package com.cloudmart.service;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Customer;

public interface CustomerService {
    public Response createCustomer(Customer customer);

    public Response updateCustomer(Customer customer);

    public Response getCustomerById(int customerId);

    public Response getAllCustomer();

    public Response deleteCustomerById(int customerId);

}
