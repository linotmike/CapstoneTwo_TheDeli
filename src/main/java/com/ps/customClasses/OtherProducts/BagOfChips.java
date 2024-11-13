package com.ps.customClasses.OtherProducts;

import com.ps.customClasses.Product;

public class BagOfChips implements Product {

    private String type;
    private static final double price = 1.50;

    public BagOfChips(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "BagOfChips{" +
                "type='" + type + '\'' +
                '}';
    }
}
