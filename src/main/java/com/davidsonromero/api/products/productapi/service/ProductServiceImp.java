package com.davidsonromero.api.products.productapi.service;

import com.davidsonromero.api.products.productapi.dtos.CreateProductDTO;
import com.davidsonromero.api.products.productapi.dtos.UpdateProductDTO;
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
    public Product createProduct(CreateProductDTO product) {
        Product productToCreate = product.toProduct();
        productToCreate.setPrice(cutToTwoDecimalPlaces(productToCreate.getPrice()));
        ProductVerificationResult result = verifyProductAttributes(productToCreate);
        if(!result.isValid()){
            throw new IllegalArgumentException(result.getMessage());
        }
        return productRepository.save(productToCreate);
    }

    public Product updateProduct(UpdateProductDTO product) {
        Product productToUpdate = product.toProduct();
        productToUpdate.setPrice(cutToTwoDecimalPlaces(productToUpdate.getPrice()));
        ProductVerificationResult result = verifyProductAttributes(productToUpdate);
        if(!result.isValid()){
            throw new IllegalArgumentException(result.getMessage());
        }
        return productRepository.save(productToUpdate);
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

    public Product getProduct(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("You must provide a valid id");
        }
        return productRepository.findById(id).get();
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getAllProductsPaged(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
