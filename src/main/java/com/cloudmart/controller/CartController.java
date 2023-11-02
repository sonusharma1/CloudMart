package com.cloudmart.controller;

import com.cloudmart.dto.Response;
import com.cloudmart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getCartItems/{customerId}")
    public ResponseEntity<Response> findCartItems(@PathVariable("customerId") Integer customerId) {
        return new ResponseEntity<>(cartService.findCartItems(customerId), HttpStatus.OK);
    }
}
