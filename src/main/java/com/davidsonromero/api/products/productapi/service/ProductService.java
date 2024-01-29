package com.davidsonromero.api.products.productapi.service;

import com.davidsonromero.api.products.productapi.dtos.*;
import com.davidsonromero.api.products.productapi.models.Product;

public interface ProductService {
    Product createProduct(CreateProductDTO product);
    Product updateProduct(UpdateProductDTO product);
    Product getProduct(Long id);
    Iterable<Product> getAllProducts();
    Iterable<Product> getAllProductsPaged(int page, int size);
    void deleteProduct(Long id);
}
