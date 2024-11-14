package com.ps.customClasses;

import com.ps.DeliFileManager;
import com.ps.customClasses.OtherProducts.BagOfChips;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;


    public Order() {
        this.products = new ArrayList<>();
    }

    public Order(List<Product> products) {
        if (products != null) {
            this.products = products;
        } else {
            this.products = new ArrayList<>();
        }

    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order Details:\n");
        stringBuilder.append("-----------------------\n");
        List<String> chipsTypes = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof BagOfChips chips) {
//                chipsTypes.add(((BagOfChips) product).getType() + chipsTypes.getPrice());
                chipsTypes.add(chips.getType() + " ($" + String.format("%.2f", chips.getPrice()) + ")");

            } else {
                stringBuilder.append(" - ").append(product).append("\n");
            }
        }
        if (!chipsTypes.isEmpty()) {
            stringBuilder.append("Bag of Chips Details:\nType:\n");
            stringBuilder.append(String.join(", ", chipsTypes)).append("\n");
        }
        stringBuilder.append("-----------------------\n");
//        stringBuilder.append(String.format("Total Price: %.2f", getTotalPrice()));
        return stringBuilder.toString();
    }


}
