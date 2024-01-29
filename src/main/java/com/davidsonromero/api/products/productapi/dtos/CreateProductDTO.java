package com.davidsonromero.api.products.productapi.dtos;

import com.davidsonromero.api.products.productapi.models.Product;

public class CreateProductDTO {
    private String name;
    private String description;
    private double price;

    public CreateProductDTO() {
    }

    public CreateProductDTO(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product toProduct(){
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        return product;
    }
}
