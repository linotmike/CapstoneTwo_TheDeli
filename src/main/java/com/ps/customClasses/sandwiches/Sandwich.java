package com.ps.customClasses.sandwiches;

import com.ps.customClasses.Product;
import com.ps.customClasses.Topping;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;


import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {
    private Size size;
    private BreadType breadType;
    private List<Topping> toppings;
    private boolean isToasted;


    @Override
    public double getPrice() {
        return 0;
    }
}
