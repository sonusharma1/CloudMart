package com.cloudmart.serviceimpl;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Customer;
import com.cloudmart.entity.Product;
import com.cloudmart.repository.ProductRepository;
import com.cloudmart.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class ProductImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Response addProduct(Product product) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            if (product != null) {
                Product newProduct = productRepository.save(product);
                response.setSuccess(true);
                response.setResponseData(newProduct);
            } else {
                response.getErrorMessages().add("Product Not Inserted");
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Product Not Added");
            log.error("Error in addProduct {}", e);
        }
        return response;
    }

    @Override
    public Response deleteProduct(int productId) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            boolean customerExists = productRepository.existsById(productId);
            if (customerExists) {
                productRepository.deleteById(productId);
                response.setSuccess(true);
            } else {
                response.getErrorMessages().add("Product Not Found");
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Product Not Deleted");
            log.error("Error in deleteProduct {}", e);
        }
        return response;
    }

    @Override
    public Response viewAllProducts() {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            List<Product> products = productRepository.findAll();
            ;
            if (!products.isEmpty()) {
                response.setSuccess(true);
                response.setResponseData(products);
            } else {
                response.getErrorMessages().add("No products");
            }
        } catch (Exception e) {
            log.error("Error in viewAllProducts() {}", e);
        }
        return response;
    }

    @Override
    public Response updateProduct(Product product) {
        Response response = new Response(false, new ArrayList<>(), null);
        try {
            boolean productExists = productRepository.existsById(product.getId());
            if (productExists) {
                Product updatedProduct = productRepository.save(product);
                response.setSuccess(true);
                response.setResponseData(updatedProduct);
            } else {
                response.getErrorMessages().add("Product Not Found");
            }
        } catch (Exception e) {
            response.getErrorMessages().add("Product Not Updated");
            log.error("Error in  updateProduct {}", e);
        }
        return response;
    }
}
