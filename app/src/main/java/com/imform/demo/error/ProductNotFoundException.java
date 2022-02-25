package com.imform.demo.error;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("User not found" + id);
    }
}
