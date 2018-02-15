package com.codecool.plaza.api;

import com.codecool.plaza.Exceptions.NoSuchShopException;
import com.codecool.plaza.Exceptions.PlazaIsClosedException;
import com.codecool.plaza.Exceptions.ShopAlreadyExistException;

import java.util.List;

public class PlazaImplementation implements Plaza {

    private boolean openingHours;
    private List<Shop> shops;

    public PlazaImplementation(){}

    @Override
    public List<Shop> getShop() throws PlazaIsClosedException {
        return shops;
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistException, PlazaIsClosedException {
        if(!(shops.contains(shop))) {
            shops.add(shop);
        } else throw new ShopAlreadyExistException("Shop exist, sry");
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if(shops.contains(shop)){
            shops.remove(shop);
        }else throw new NoSuchShopException("Shop not exist");
    }

    @Override
    public Shop findShopByName(String shop) throws NoSuchShopException, PlazaIsClosedException {
        return null;
    }

    @Override
    public boolean isOpen() {
        return openingHours;
    }

    @Override
    public void open() {
        openingHours = true;
    }

    @Override
    public void close() {
        openingHours = false;
    }
}
