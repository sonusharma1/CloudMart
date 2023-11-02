package com.cloudmart.controller;


import com.cloudmart.dto.Response;
import com.cloudmart.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/customerOrder")
public class CutomerOrderController {

    @Autowired
    CustomerOrderService customerOrderServiceImpl;


    @PostMapping("/processOrder")
    public ResponseEntity<Response> processOrder(
            @RequestParam(name = "customerId", required = true) Integer customerId
    ) {
        Response response = customerOrderServiceImpl.ProcessOrder(customerId);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }


}
