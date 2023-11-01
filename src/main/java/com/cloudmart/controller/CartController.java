package com.cloudmart.controller;

import com.cloudmart.dto.Response;
import com.cloudmart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<Response> addToCart(
            @RequestParam(name = "customerId", required = true) Integer customerId,
            @RequestParam(name = "productId", required = true) Integer productId,
            @RequestParam(name = "quantity", required = true) Integer quantity
    ) {
        Response response = cartService.addtoCart(customerId, productId, quantity);
        return new ResponseEntity<Response>(response, response.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

@GetMapping("viewCart")
public ResponseEntity<Response> viewCart(
        @RequestParam(name = "customerId", required = true) Integer customerId
) {
    Response response = cartService.getCartItems(customerId);
    return new ResponseEntity<Response>(response, response.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST);
}

}
