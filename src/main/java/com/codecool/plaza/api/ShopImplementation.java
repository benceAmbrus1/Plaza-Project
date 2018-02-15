package com.codecool.plaza.api;

import com.codecool.plaza.Exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopImplementation implements Shop {

    private String name;
    private String owner;
    private boolean shopOpeningHours;
    private Map<Long, ShopImplEntry> products;
    private List<Product> productList = new ArrayList<>();

    public ShopImplementation(String name, String owner){
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public boolean isOpen() {
        return shopOpeningHours;
    }

    @Override
    public void open() {
        shopOpeningHours = true;
    }

    @Override
    public void close() {
        shopOpeningHours = false;
    }

    @Override
    public List<Product> getProducts() throws ShopIsClosedException {
        if(shopOpeningHours) {
            List<ShopImplEntry> temp = new ArrayList<>();
            temp.addAll(products.values());
            for (ShopImplEntry product : temp) {
                productList.add(product.getProduct());
            }
        return productList;
        }else throw new ShopIsClosedException("sry its closed");
    }


    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        if(shopOpeningHours){
            Product temp = null;
            for(Product prod:getProducts()){
                if(prod.getName().equals(name)){
                    temp = prod;
                }
            }if(temp == null){
                throw new NoSuchProductException("Sry, I not find this production");
            }
            return temp;
        }else throw new ShopIsClosedException("Sry its closed.");
    }

    @Override
    public float getPrice(long barcode) {
        return 0;
    }

    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if(shopOpeningHours) {
            if (products.containsKey(barcode)) {
                return true;
            } else return false;
        }else throw new ShopIsClosedException("Sry, its closed.");
    }

    @Override
    public void addNewProduct(long barcode, Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if(shopOpeningHours){
            for(Product prod:getProducts()){
                if(prod.getName().equals(product.getName())){
                    throw new ProductAlreadyExistsException("This product already added");
                }
            }
            products.put(barcode, new ShopImplEntry(product, quantity, price));
        }else throw new ShopIsClosedException("Sry, it's closed");
    }

    @Override
    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {

    }

    @Override
    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    private class ShopImplEntry{

        private Product product;
        private int quantity;
        private float price;

        ShopImplEntry(Product product, int quantity, float price){
            this.product = product;
            this.quantity= quantity;
            this.price = price;
        }

        public Product getProduct(){
            return product;
        }

        public void setProduct(Product product){
            this.product = product;
        }

        public int getQuantity(){
            return quantity;
        }

        public void setQuantity(int quantity){
            this.quantity = quantity;
        }

        public void increaseQuantity(int amount){
            quantity += amount;
        }

        public void decreaseQuantity(int amount){
            quantity -= amount;
        }

        public float getPrice(){
            return price;
        }

        public void setPrice(int price){
            this.price = price;
        }

        public String toString(){return null;}
    }
}
