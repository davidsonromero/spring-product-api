package com.davidsonromero.api.products.productapi.service;

public class ProductVerificationResult {
    private boolean isValid;
    private String message;

    public ProductVerificationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public ProductVerificationResult() {
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
