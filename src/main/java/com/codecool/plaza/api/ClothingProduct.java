package com.codecool.plaza.api;

public class ClothingProduct extends Product {

    private String type;
    private String material;

    public ClothingProduct(String name, long barcode, String manufacturer, String type, String material) {
        super(name, barcode, manufacturer);
        this.type = type;
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
