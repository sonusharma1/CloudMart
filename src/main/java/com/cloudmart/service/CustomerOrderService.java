package com.cloudmart.service;

import com.cloudmart.dto.Response;

public interface CustomerOrderService {
    public Response ProcessOrder(Integer customerId);

}
