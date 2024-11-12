package com.ps.customClasses.sandwiches;

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

    public Sandwich(String name, Size size, BreadType breadType, boolean isToasted) {
        this.name = name;
        this.size = size;
        this.breadType = breadType;
        this.toppings = new ArrayList<>();
        this.isToasted = isToasted;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }
    public void removeTopping(Topping topping){
        toppings.remove(topping);
    }
    private String getName(){
        return name;
    }
    private void setName(String name){
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
        return 0;
    }
}
