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
    public void setPrice(double price){

    }

    @Override
    public String toString() {
        return "Bag of Chips Details:\n" +
                "-----------------------\n" +
                String.format("  %-6s : '%s'\n", "Type", type) +
                "-----------------------";
    }

}
