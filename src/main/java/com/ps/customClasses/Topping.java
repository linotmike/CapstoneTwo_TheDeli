package com.ps.customClasses;

import com.ps.customClasses.enums.Size;
import com.ps.customClasses.enums.ToppingType;
import com.ps.customClasses.Helper.PriceCalculator;

public class Topping implements Product {

    private String name;
    private double price;
    private ToppingType toppingType;

    public Topping( String name ,double price, Size size, ToppingType toppingType) {
        this.name = name;
        this.price = PriceCalculator.getToppingPrice(size, toppingType);
        this.toppingType = toppingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ToppingType getToppingType() {
        return toppingType;
    }

    public void setToppingType(ToppingType toppingType) {
        this.toppingType = toppingType;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", name, price);
    }

}
