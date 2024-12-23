package com.ps.customClasses.OtherProducts;

import com.ps.customClasses.Product;
import com.ps.customClasses.enums.Size;
import com.ps.customClasses.Helper.PriceCalculator;

public class Drink implements Product {

  private Size size;
  private String type;
  private String flavor;
  private double price;

  public Drink(Size size, String type, String flavor,double price){
    this.size = size;
    this.type = type;
    this.flavor = flavor;
    this.price = PriceCalculator.getDrinkPrice(size);
  }

  public Size getSize(){
    return size;
  }
  public void setSize(Size size){
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(String flavor) {
    this.flavor = flavor;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
    public double getPrice() {
        return price;
    }

  @Override
  public String toString() {
    return "Drink Details:\n" +
            "-----------------------\n"+
            String.format("  %-8s: %s\n", "Size", size) +
            String.format("  %-8s: '%s'\n", "Type", type) +
            String.format("  %-8s: '%s'\n", "Flavor", flavor) +
            String.format("  %-8s: $%.2f\n", "Price", price) +
            "-----------------------";
  }

}
