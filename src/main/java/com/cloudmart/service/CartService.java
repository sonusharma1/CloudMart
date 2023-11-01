package com.cloudmart.service;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Cartitems;

import java.util.List;

public interface CartService {
    Response addtoCart(Integer customerId, Integer productId, Integer quantity);

    Response removeFromCart(Integer customerId, Integer CartitemsId);

    Response removeAllFromCart(Integer customerId);
}
