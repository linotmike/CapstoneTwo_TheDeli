package com.ps.customClasses;

import com.ps.customClasses.enums.Size;
import com.ps.customClasses.enums.ToppingType;

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

    public static double getToppingPrice(Size size, ToppingType toppingType) {
        if(toppingType.isPremium()) {
            switch (toppingType) {
                case MEAT:
                    switch (size) {
                        case SMALL:
                            return 1.0;
                        case MEDIUM:
                            return 2.0;
                        case LARGE:
                            return 3.0;
                        default:
                            throw new IllegalArgumentException("Invalid size: " + size);
                    }
                case CHEESE:
                    switch (size) {
                        case SMALL:
                            return .75;
                        case MEDIUM:
                            return 1.50;
                        case LARGE:
                            return 2.25;
                        default:
                            throw new IllegalArgumentException("Invalid size: " + size);

                    }
                default:
                    throw new IllegalArgumentException("Invalid topping type: " + toppingType);


            }

        }else {
            return 0.0;
        }
    }
}
