package com.codecool.plaza.api;

import com.codecool.plaza.exceptions.NoSuchShopException;
import com.codecool.plaza.exceptions.PlazaIsClosedException;
import com.codecool.plaza.exceptions.ShopAlreadyExistException;

import java.util.ArrayList;
import java.util.List;

public class PlazaImplementation implements Plaza {

    private String name;
    private boolean openingHours;
    private List<Shop> shops = new ArrayList<>();

    public PlazaImplementation(String name){
        this.name = name;
    }

    @Override
    public List<Shop> getShop() throws PlazaIsClosedException {
        if(openingHours){
            return shops;
        } else throw new PlazaIsClosedException("Sry its closed.");
    }

    @Override
    public void addShop(Shop shop) throws ShopAlreadyExistException, PlazaIsClosedException {
        if(openingHours) {
            for(Shop sp:shops){
                if(sp.getName().equals(shop.getName())){
                    throw new ShopAlreadyExistException("Shop already exist, sry");
                }
            }
            shops.add(shop);
        }else throw new PlazaIsClosedException("Sry its closed.");
    }

    @Override
    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if(openingHours){
            if(shops.contains(shop)){
                shops.remove(shop);
            }else throw new NoSuchShopException("Shop not exist");
        }else throw new PlazaIsClosedException("Sry its closed");
    }

    @Override
    public Shop findShopByName(String shop) throws NoSuchShopException, PlazaIsClosedException {
        if(openingHours) {
            Shop result = null;
            for (Shop shop1 : shops) {
                if (shop1.getName().equals(shop)) {
                    result = shop1;
                }
            }
            if (result == null){
                throw new NoSuchShopException("Shop you request does not exist");
            }
            return result;
        }else throw new PlazaIsClosedException("Sry its closed");
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
