package com.davidsonromero.api.products.productapi.service;

import com.davidsonromero.api.products.productapi.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProduct(Long id);
    Iterable<Product> getAllProducts();
    Iterable<Product> getAllProductsPaged(int page, int size);
    void deleteProduct(Long id);
}
