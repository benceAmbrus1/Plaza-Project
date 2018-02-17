package com.codecool.plaza.api;

import com.codecool.plaza.exceptions.NoSuchShopException;
import com.codecool.plaza.exceptions.PlazaIsClosedException;
import com.codecool.plaza.exceptions.ShopAlreadyExistException;

import java.util.List;

public interface Plaza {

    List<Shop> getShop() throws PlazaIsClosedException;
    void addShop(Shop shop) throws ShopAlreadyExistException, PlazaIsClosedException;
    void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException;
    Shop findShopByName(String shop) throws NoSuchShopException, PlazaIsClosedException;
    boolean isOpen();
    void open();
    void close();
    String toString();
}
