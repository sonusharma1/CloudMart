package com.cloudmart.service;

import com.cloudmart.dto.Response;
import com.cloudmart.entity.Product;

public interface ProductService {

    Response addProduct(Product product);

    Response deleteProduct(int productId);

    Response viewAllProducts();

    Response updateProduct(Product product);

}