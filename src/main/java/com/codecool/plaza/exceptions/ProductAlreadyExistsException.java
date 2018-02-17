package com.codecool.plaza.exceptions;

public class ProductAlreadyExistsException extends ShopException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
