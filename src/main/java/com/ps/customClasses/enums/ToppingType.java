package com.ps.customClasses.enums;

public enum ToppingType {
    MEAT(true),
    CHEESE(true),
    VEGGIE(false);

    private final boolean isPremium;

    ToppingType(boolean isPremium){
        this.isPremium = isPremium;
    }

    public boolean isPremium() {
        return isPremium;
    }
}
