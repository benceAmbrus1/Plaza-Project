package com.codecool.plaza.api;

import com.codecool.plaza.Exceptions.*;

import java.util.List;

public interface Shop {

    String getName();
    String getOwner();
    boolean isOpen();
    void open();
    void close();
    List<Product> getProducts() throws ShopIsClosedException;
    Product findByName(String name) throws NoSuchProductException, ShopIsClosedException;
    float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException;
    boolean hasProduct(long barcode) throws ShopIsClosedException;
    void addNewProduct(long barcode, Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException;
    void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException;
    Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;
    List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException;
    String toString();
}
