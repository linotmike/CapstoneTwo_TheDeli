package com.ps.customClasses.sandwiches;

import com.ps.customClasses.Topping;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;
import com.ps.customClasses.enums.ToppingType;

public class BltSandwich extends Sandwich{
    public BltSandwich() {
        super("BLT", Size.MEDIUM, BreadType.WHITE, true, 7.00);
        topping();

    }

    private void topping(){
        addTopping(new Topping("Bacon",1.50, Size.MEDIUM, ToppingType.MEAT));
        addTopping(new Topping("Cheddar",1.00, Size.MEDIUM, ToppingType.CHEESE));
        addTopping(new Topping("Lettuce",0.50, null, ToppingType.VEGGIE));
        addTopping(new Topping("Tomato",0.50, null, ToppingType.VEGGIE));
        addTopping(new Topping("Ranch",0.00, null, ToppingType.VEGGIE));
    }
}
