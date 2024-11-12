package com.ps.customClasses;

import com.ps.customClasses.enums.Size;

public class Drink implements Product{

  private Size size;
  private String type;
  private String flavor;
  private double price;


    @Override
    public double getPrice() {
        return 0;
    }
}
