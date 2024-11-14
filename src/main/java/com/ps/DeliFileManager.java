package com.ps;

import com.ps.customClasses.Order;
import com.ps.customClasses.OtherProducts.BagOfChips;
import com.ps.customClasses.OtherProducts.Drink;
import com.ps.customClasses.Product;
import com.ps.customClasses.Topping;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;
import com.ps.customClasses.enums.ToppingType;
import com.ps.customClasses.sandwiches.Sandwich;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DeliFileManager {


    public static Order readOrder() {
        Order order = new Order();
        Sandwich currentSandwich = null;
        String customerName = "Customer Name";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("order.csv"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if(line.endsWith("Order")){
                    customerName = line.substring(0, line.indexOf("'s Order")).trim();
                    continue;

                }

                if (line.startsWith("Product|Type|Size|Flavor|Price")
                        || line.contains("Order placed on") || line.startsWith("Total Price") ||
                        line.startsWith("----------------------------------------------------") || line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split("\\|", -1);

                if (!line.startsWith("-")) {
                    if (fields.length == 3 && fields[0].equalsIgnoreCase("Chips")) {
                        String type = fields[1].trim();
                        BagOfChips chips = new BagOfChips(type);
                        order.addProduct(chips);

                    } else if (fields.length >= 4) {
                        String productType = fields[0].trim();
                        String type = fields[1].trim();
                        String size = fields[2].trim();
                        String flavor = fields[3].trim();
                        double price;

                        try {
                            price = Double.parseDouble(fields[fields.length - 1].replace("$", "").trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Failed to parse: " + line);
                            continue;
                        }

                        if (productType.equals("Sandwich")) {
                            BreadType breadType = !type.isEmpty() ? BreadType.valueOf(type.toUpperCase()) : null;
                            Size sandwichSize = !size.isEmpty() && !size.equals("N/A") ? Size.valueOf(size.toUpperCase()) : null;
                            currentSandwich = new Sandwich(customerName, sandwichSize, breadType, false, price);
                            order.addProduct(currentSandwich);
                        } else if (productType.equals("Drinks")) {
                            Size drinkSize = !size.isEmpty() && !size.equals("N/A") ? Size.valueOf(size.toUpperCase()) : null;
                            Drink drink = new Drink(drinkSize, type, flavor, price);
                            order.addProduct(drink);
                        } else {
                            System.out.println("Unknown product type: " + productType);
                        }

                    }
                } else if (line.startsWith("-") && fields.length == 4) {
                    if (currentSandwich != null) {
                        String toppingName = fields[0].substring(1).trim();
                        String toppingType = fields[1].trim();
                        String toppingSize = fields[2].trim();
                        double toppingPrice;

                        try {
                            toppingPrice = Double.parseDouble(fields[3].replace("$", "").trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Failed to parse topping price for line: " + line);
                            continue;
                        }

                        Topping topping = new Topping(toppingName, toppingPrice,
                                !toppingSize.isEmpty() && !toppingSize.equals("N/A") ? Size.valueOf(toppingSize.toUpperCase()) : null,
                                ToppingType.valueOf(toppingType.toUpperCase())
                        );
                        currentSandwich.addTopping(topping);
                    } else {
                        System.out.println("No sandwich found for topping: " + line);
                    }
                } else {
                    System.out.println("Unexpected format for line: " + line);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the file");
        }
        System.out.println("Order loaded: " + order);
        return order;
    }

    public static void saveOrder(Order order) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("order.csv", true));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeStamp = LocalDateTime.now().format(dateTimeFormatter);
            String customerName = customerName(order);
            if (!customerName.isEmpty()) {
                bufferedWriter.write("\n----------------------------------------------------\n");
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
            formmatedProduct.append("Sandwich|").append(sandwich.getBreadType() != null ? sandwich.getBreadType() : "").append("|").append(sandwich.getSize() != null ? sandwich.getSize() : "").append("|").append(String.format("$%.2f", price));
            for (Topping topping : sandwich.getToppings()) {
                formmatedProduct.append(String.format(" \n-%s|%s|%s|$%.2f", topping.getName() != null ? topping.getName() : "N/A", topping.getToppingType() != null ? topping.getToppingType() : "N/A", topping.getSize() != null ? topping.getSize() : "N/A", topping.getPrice()));
            }

//            name = sandwich.getName();
//            type = "Sandwich";
//            size = sandwich.getSize().toString();
        } else if (product instanceof Drink) {
            Drink drink = (Drink) product;
            formmatedProduct.append("Drinks|").append("").append(drink.getType() != null ? drink.getType() : "").append("|").append(drink.getSize() != null ? drink.getSize() : "").append("|").append(drink.getFlavor() != null ? drink.getFlavor() : "").append("|").append(String.format("$%.2f", price));
//            type = drink.getType();
//            size = drink.getSize().toString();
//            flavor = drink.getFlavor();
        } else if (product instanceof BagOfChips) {
            BagOfChips bagOfChips = (BagOfChips) product;
            formmatedProduct.append("Chips|").append(bagOfChips.getType() != null ? bagOfChips.getType() : "").append("|").append(String.format("$%.2f", price));
//            type = "Chips";
//            name = bagOfChips.getType();
        }


        return formmatedProduct.toString();
    }
}
