package com.ps.customClasses.sandwiches;

import com.ps.customClasses.Helper.PriceCalculator;
import com.ps.customClasses.Product;
import com.ps.customClasses.Topping;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;


import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {
    private String name;
    private Size size;
    private BreadType breadType;
    private List<Topping> toppings;
    private boolean isToasted;
    private double price;

    public Sandwich(String name, Size size, BreadType breadType, boolean isToasted, double price) {
        this.name = name;
        this.size = size;
        this.breadType = breadType;
        this.toppings = new ArrayList<>();
        this.isToasted = isToasted;
        this.price = PriceCalculator.getBreadPrice(size);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
        this.price += topping.getPrice();
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
        this.price -= topping.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean isToasted) {
        this.isToasted = isToasted;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sandwich Details:\n");
        stringBuilder.append("-----------------------\n");
        stringBuilder.append("Name: ").append(name).append("\n");
        stringBuilder.append("Sandwich Size: ").append(size).append("\n");
        stringBuilder.append("Price: ").append(getPrice()).append("\n");
        stringBuilder.append("Bread Type: ").append(breadType).append("\n");
        stringBuilder.append("Toasted: ").append(isToasted ? "Yes" : "No").append("\n");
        stringBuilder.append("Added: \n");

        if (toppings.isEmpty()) {
            stringBuilder.append("None");
        } else {
            toppings.forEach(topping -> stringBuilder.append("  - ").append(topping).append("\n"));

        }

//        for (Topping topping: toppings){
//            stringBuilder.append(" - ").append(topping).append("\n");
//        }
        stringBuilder.append("-----------------------\n");
        return stringBuilder.toString();

//        return "Sandwich{" +
//                "name='" + name + '\'' +
//                ", size=" + size +
//                ", breadType=" + breadType +
//                ", toppings=" + toppings +
//                ", isToasted=" + isToasted +
//                '}';
    }
}
