package com.ps.customClasses;

import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;

public class PriceCalculator {

    public static double getBreadPrice(Size size) {
        switch (size) {
            case SMALL:
                return 5.50;
            case MEDIUM:
                return 7.0;
            case LARGE:
                return 8.50;
            default:
                throw new IllegalArgumentException("Invalid Size :" + size);


        }

    }
}
