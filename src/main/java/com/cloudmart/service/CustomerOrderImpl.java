package com.cloudmart.service;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Cartitems;
import com.cloudmart.entity.Customer;
import com.cloudmart.repository.CartitemsRepository;
import com.cloudmart.repository.CustomerRepository;
import com.cloudmart.entity.Orderitem;
import com.cloudmart.entity.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerOrderImpl implements CustomerOrderService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartitemsRepository cartitemsRepository;

    @Override
    public Response ProcessOrder(Integer customerId) {
        Response response = new Response();
        try {

            Customer customer = customerRepository.findById(customerId).get();
            List<Cartitems> cartitemsList = customer.getCart().getCartitemsList();

            CustomerOrder customerOrder = new CustomerOrder();


            List<Orderitem> collect = cartitemsList.stream().map(cartitems -> {
                Orderitem orderitem = new Orderitem();
                orderitem.setProduct(cartitems.getProduct());
                orderitem.setQuantity(cartitems.getQuantity());
                orderitem.setTotalPrice(cartitems.getProduct().getPrice() * cartitems.getQuantity());
                orderitem.setCustomerOrder(customerOrder);
                return orderitem;
            }).collect(Collectors.toList());


            double finalTotalPrice = cartitemsList.stream().mapToDouble(cartitem -> cartitem.getTotalPrice()).sum();
            customerOrder.setCustomer(customer);
            customerOrder.setLocalDate(LocalDate.now());
            customerOrder.setOrderitemList(collect);
            customerOrder.setFinalTotalPrice(finalTotalPrice);

            cartitemsList.forEach(cartItem -> cartitemsRepository.deleteCartItemById((long) cartItem.getId()));


            response.setResponseData("Order Process done");
            response.setSuccess(true);

        } catch (Exception exception) {
            System.out.println("Error in processOrder");
        }
        return response;
    }
}
