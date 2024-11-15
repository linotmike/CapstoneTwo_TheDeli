package com.ps.customClasses.sandwiches;

import com.ps.customClasses.Topping;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;
import com.ps.customClasses.enums.ToppingType;

public class PhillyCheeseSteak extends Sandwich {


    public PhillyCheeseSteak() {
        super("Philly Cheese Steak", Size.MEDIUM, BreadType.WHITE, true, 8.00);
        topping();
    }

    private void topping (){
        addTopping(new Topping("Steak",1.00,Size.MEDIUM, ToppingType.MEAT));
        addTopping(new Topping("American Cheese",1.00,Size.MEDIUM, ToppingType.CHEESE));
        addTopping(new Topping("Peppers",0.50,Size.MEDIUM, ToppingType.VEGGIE));
        addTopping(new Topping("Mayo",0.00,null, ToppingType.VEGGIE));
    }

}
