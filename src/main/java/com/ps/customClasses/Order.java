package com.ps.customClasses;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;

    public Order(){
        this.products = new ArrayList<>();
    }

    public Order(List<Product> products) {
        if(products != null){
            this.products = products;
        } else {
            this.products = new ArrayList<>();
        }

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
