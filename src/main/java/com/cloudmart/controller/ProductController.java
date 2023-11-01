package com.cloudmart.controller;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Customer;
import com.cloudmart.entity.Product;
import com.cloudmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Response> addProduct(@RequestBody Product product) {
        Response response = productService.addProduct(product);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Response> deleteProduct(@PathVariable int productId) {
        Response response = productService.deleteProduct(productId);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Response> updateProduct(@RequestBody Product product) {
        Response response = productService.updateProduct(product);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/viewAllProducts")
    public ResponseEntity<Response> viewAllProducts() {
        Response response = productService.viewAllProducts();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
