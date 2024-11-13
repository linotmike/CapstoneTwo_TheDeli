package com.ps;

import com.ps.customClasses.Order;
import com.ps.customClasses.Product;

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
            bufferedWriter.write("Order placed on: " + timeStamp + " \n");
            bufferedWriter.write("Product|Type|Size|Flavor|Price\n");
            for (Product product : order.getProducts()) {
                bufferedWriter.write(formatProduct(product) + "\n");
            }
                bufferedWriter.write("----------------------------------------------------\n");
                bufferedWriter.write(String.format("Total Price: $.2f%n",order.getTotalPrice()));
                bufferedWriter.write("\n");

//            String firstLine = String.format("%s|")
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        } finally {
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the writer");
            }
        }
    }

    private static String formatProduct(Product product) {
        String name = "";
        String type = "";
        String size = "";
        String flavor = "";
        double price = product.getPrice();

        return String.format("%s|%s|%s|%s|%.2f", name, type, size, flavor, price);
    }
}
