package com.ps.customClasses.enums;

public enum Size {
    SMALL(4.0),

    MEDIUM(8.0),

    LARGE(12.0);

    private final double size;

    Size(double size){
        this.size = size;
    }

    public double getSize(){
        return size;
    }

}
