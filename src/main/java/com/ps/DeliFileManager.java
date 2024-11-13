package com.ps;

import com.ps.customClasses.Order;
import com.ps.customClasses.OtherProducts.BagOfChips;
import com.ps.customClasses.OtherProducts.Drink;
import com.ps.customClasses.Product;
import com.ps.customClasses.Topping;
import com.ps.customClasses.sandwiches.Sandwich;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DeliFileManager {

    public static void saveOrder(Order order) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("order.csv", true));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeStamp = LocalDateTime.now().format(dateTimeFormatter);
            String customerName = customerName(order);
            if (!customerName.isEmpty()) {
                bufferedWriter.write("----------------------------------------------------\n");
                bufferedWriter.write("\n" + customerName + "'s Order\n");
            }
            bufferedWriter.write("Order placed on: " + timeStamp + " \n");
            bufferedWriter.write("Product|Type|Size|Flavor|Price\n");
            for (Product product : order.getProducts()) {
                bufferedWriter.write(formatProduct(product) + "\n");
            }
            bufferedWriter.write("----------------------------------------------------\n");
            bufferedWriter.write(String.format("Total Price: $%.2f", order.getTotalPrice()));
            bufferedWriter.write("\n");

//            String firstLine = String.format("%s|")
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the writer");
            }
        }
    }

    private static String customerName(Order order) {
        for (Product product : order.getProducts()) {
            if (product instanceof Sandwich) {
                return ((Sandwich) product).getName();
            }
        }
        return "";
    }

    private static String formatProduct(Product product) {
        StringBuilder formmatedProduct = new StringBuilder();
//        String name = "";
//        String type = "";
//        String size = "";
//        String flavor = "";
        double price = product.getPrice();
        if (product instanceof Sandwich) {
            Sandwich sandwich = (Sandwich) product;
            formmatedProduct.append("Sandwich|")
                    .append(sandwich.getBreadType()).append("|")
                    .append(sandwich.getSize()).append("|")
                    .append(String.format("$%.2f", price));
            for (Topping topping : sandwich.getToppings()) {
                formmatedProduct.append(String.format(" \n-%s|%s|%s|$%.2f", topping.getName(), topping.getToppingType(),topping.getSize(), topping.getPrice()));
            }

//            name = sandwich.getName();
//            type = "Sandwich";
//            size = sandwich.getSize().toString();
        } else if (product instanceof Drink) {
            Drink drink = (Drink) product;
            formmatedProduct.append("Drink|")
                    .append("").append(drink.getType())
                    .append("|").append(drink.getSize())
                    .append("|").append(drink.getFlavor())
                    .append("|").append(String.format("$%.2f", price));
//            type = drink.getType();
//            size = drink.getSize().toString();
//            flavor = drink.getFlavor();
        } else if (product instanceof BagOfChips) {
            BagOfChips bagOfChips = (BagOfChips) product;
            formmatedProduct.append("Chips|")
                    .append(bagOfChips.getType()).append("|")
                    .append(String.format("$%.2f", price));
//            type = "Chips";
//            name = bagOfChips.getType();
        }


        return formmatedProduct.toString();
    }
}
