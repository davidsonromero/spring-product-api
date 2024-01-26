package com.davidsonromero.api.products.productapi.service;

import com.davidsonromero.api.products.productapi.models.Product;
import com.davidsonromero.api.products.productapi.models.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) {
        product.setPrice(cutToTwoDecimalPlaces(product.getPrice()));
        if(!verifyProductAttributes(product).isValid()){
            throw new IllegalArgumentException(verifyProductAttributes(product).getMessage());
        }
        return productRepository.save(product);
    }

    public ProductVerificationResult verifyProductAttributes(Product product){
        ProductVerificationResult result = new ProductVerificationResult();
        if(product.getName() == null || product.getName().isEmpty() || product.getName().isBlank()) {
            result.setValid(false);
            result.setMessage("Product name is required");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty() || product.getDescription().isBlank()) {
            result.setValid(false);
            result.setMessage("Product description is required");
        }
        if(product.getId() != null){
            Product productToUpdate = productRepository.findById(product.getId()).get();
            if(productToUpdate == null){
                result.setValid(false);
                result.setMessage("Product not found");
            }
        }
        if (product.getPrice() <= 0) {
            result.setValid(false);
            result.setMessage("Product price must be greater than 0");
        }

        result.setValid(true);
        result.setMessage("Product is valid");
        return result;
    }

    private double cutToTwoDecimalPlaces(double price) {
        BigDecimal bd = new BigDecimal(Double.toString(price));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public Product getProduct(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("You must provide a valid id");
        }
        return productRepository.findById(id).get();
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> getAllProductsPaged(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
