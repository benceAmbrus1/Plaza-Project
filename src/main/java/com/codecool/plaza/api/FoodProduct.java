package com.codecool.plaza.api;

public class FoodProduct extends Product {

    private int calories;
    private String type;

    public FoodProduct(String name, long barcode, String manufacturer, int calories, String type) {
        super(name, barcode, manufacturer);
        this.calories = calories;
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "";
    }
}
