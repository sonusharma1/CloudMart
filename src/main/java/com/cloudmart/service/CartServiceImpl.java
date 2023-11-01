package com.cloudmart.service;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Cart;
import com.cloudmart.entity.Cartitems;
import com.cloudmart.entity.Customer;
import com.cloudmart.entity.Product;
import com.cloudmart.repository.CartRepository;
import com.cloudmart.repository.CartitemsRepository;
import com.cloudmart.repository.CustomerRepository;
import com.cloudmart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartitemsRepository cartitemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public Response addtoCart(Integer customerId, Integer productId, Integer quantity) {
        Response response = new Response();
        try {

            Product product = productRepository.findById(productId).get();

            Cartitems cartitems = new Cartitems();
            cartitems.setQuantity(quantity);
            cartitems.setProduct(product);
            cartitems.setTotalPrice(quantity * product.getPrice());

            Customer customer = customerRepository.findById(customerId).get();
            Cart cart = customer.getCart();
            cartitems.setCart(cart);
            cartitemsRepository.save(cartitems);
            cart.getCartitemsList().add(cartitems);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.getErrorMessages().add("Failed to adding in cart");
        }
        return response;
    }

    @Transactional
    @Override
    public Response removeFromCart(Integer customerId, Integer CartitemsId) {
        Response response = new Response();
        try {
            Customer customer = customerRepository.findById(customerId).get();
            Cart cart = customer.getCart();
            Cartitems cartitem = cartitemsRepository.findById(CartitemsId).get();
            cart.getCartitemsList().remove(cartitem);
            cartitem.setCart(null);
            cartitem.setProduct(null);
            cartitemsRepository.deleteById(cartitem.getId());
            response.setSuccess(true);
            response.setResponseData("item removed from cart");
        } catch (Exception exception) {
            response.setSuccess(false);
            response.getErrorMessages().add("error in removeFromCart");
        }
        return response;
    }

    @Transactional
    @Override
    public Response removeAllFromCart(Integer customerId) {
        Response response = new Response();
        try {
            Customer customer = customerRepository.findById(customerId).get();
            Cart cart = customer.getCart();
            cart.getCartitemsList().forEach(cartitems -> {
                cartitems.setCart(null);
                cartitems.setProduct(null);
                cartitemsRepository.deleteById(cartitems.getId());
            });
            response.setSuccess(true);
            response.setResponseData("all items removed from cart");
        } catch (Exception exception) {
            response.setSuccess(false);
            response.getErrorMessages().add("error in removeFromCart");
        }
        return response;
    }

    @Override
    public Response getCartItems(Integer customerId) {
        Response response = new Response();
        try {
            Customer customer = customerRepository.findById(customerId).get();

            Cart cart = cartRepository.findById(customer.getCart().getId()).get();
            response.setResponseData(cart);
            response.setSuccess(true);

        } catch (Exception exception) {
            response.setSuccess(false);
            response.getErrorMessages().add("getCartItems");
        }
        return response;
    }
}
