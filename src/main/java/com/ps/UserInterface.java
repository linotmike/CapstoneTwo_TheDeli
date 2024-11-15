package com.ps;

import com.ps.customClasses.OtherProducts.BagOfChips;
import com.ps.customClasses.OtherProducts.Drink;
import com.ps.customClasses.Order;
import com.ps.customClasses.Helper.PriceCalculator;
import com.ps.customClasses.Product;
import com.ps.customClasses.Topping;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;
import com.ps.customClasses.enums.ToppingType;
import com.ps.customClasses.sandwiches.Sandwich;

import java.util.Scanner;

public class UserInterface {
    private static Scanner commandScanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
    private static Sandwich sandwich;
    private static Order order;

    public static void display() {
        init();
    }

    public static void init() {
        int mainMenuCommand;
        do {
            System.out.println("1) New order");
            System.out.println("2) View saved orders");
            System.out.println("0) Exit ");
            mainMenuCommand = commandScanner.nextInt();
            switch (mainMenuCommand) {
                case 1:
                    orderScreen(null);
                    break;
                case 2:
                    savedOrder();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid command please try again");
            }
        } while (mainMenuCommand != 0);


    }

    private static void savedOrder() {
        Order savedOrder = DeliFileManager.readOrder();
        if (savedOrder == null || savedOrder.getProducts().isEmpty()) {
            System.out.println("No saved orders");
        }
    }


    private static void orderScreen(Order exsistingOrder) {
        order =(exsistingOrder != null) ? exsistingOrder :new Order();
        int orderScreenCommand;
        do {
            System.out.println("1) Add sandwich");
            System.out.println("2) Add chips");
            System.out.println("3) Add drink");
            System.out.println("4) Checkout");
            System.out.println("0) Main menu");
            try{
            orderScreenCommand = commandScanner.nextInt();
            switch (orderScreenCommand) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addChips();
                    break;
                case 3:
                    addDrink();
                    break;
                case 4:
                    checkout();
                    break;
                case 0:
                    System.out.println("Welcome to The Deli");
                    init();
                    break;
                default:
                    System.out.println("Invalid command please try again");

            }

            }catch(Exception e){
                System.out.println("Invalid input try again");
            }

        } while (true);
    }

    private static void addSandwich() {
        String name;
        do {
            System.out.println("What is your name?");
            name = inputScanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. please enter your name");
            }
        } while (name.isEmpty());
        BreadType breadType = null;
        while (breadType == null) {
            try {
                System.out.println("Select your bread (Wheat,White,Rye,Wrap)");
                String bread = inputScanner.nextLine().toUpperCase();
                breadType = BreadType.valueOf(bread);

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid bread option. please enter a valid option");
            }
        }
        Size size = null;
        while (size == null) {
            try {
                System.out.println("Select the size of your sandwich (small (4in) for $5.50 , medium (8in) for $7.00 , Large (12in) for $8.50) ");
                String sizeInput = inputScanner.nextLine().toUpperCase();
                size = Size.valueOf(sizeInput);

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size option. please enter a valid option");
            }
        }
        double price = PriceCalculator.getBreadPrice(size);
        System.out.println("Would you like your sandwich to be toasted? (Yes/No)");
        boolean isToasted = inputScanner.nextLine().equalsIgnoreCase("Yes");
        Sandwich sandwich = new Sandwich(name, size, breadType, isToasted, price);
        addToppings(sandwich);
        addSide(sandwich);
        addSauce(sandwich);

        order.addProduct(sandwich);

        System.out.println("sandwich added successfully");
//        System.out.print("Name " + name + " size " + size + " bread type " + breadType + isToasted + "Sandwich" + sandwich);
        System.out.println(sandwich);

    }

    private static void addDrink() {
        System.out.println("You can add selections by using commas");
        String[] sizes, types, flavors;
        while (true) {
            System.out.println("Choose your drink size (Small (for $2.00) ,Medium (for $2.50), Large (for $3.00 )");
            String drinkSize = inputScanner.nextLine().toUpperCase();
            sizes = drinkSize.split(",");
            boolean valid = true;
            for (String size : sizes) {
                try {
                    Size.valueOf(size.trim());
                } catch (IllegalArgumentException e) {
                    valid = false;
                    System.out.println("Invalid size: " + size.trim() + " Please try again");
                    break;
                }
            }
            if (valid) break;
        }
//        Size sizeInput = Size.valueOf(drinkSize);
        while (true) {
            System.out.println("What kind of drink would you like for the (Soda,Water,Juice)");//drinkSize
            String typeInput = inputScanner.nextLine().trim();
            types = typeInput.split(",");
            boolean valid = true;
            for (String type : types) {
                if (type.trim().isEmpty()) {
                    valid = false;
                    System.out.println("Drink cannot be empty. Please enter a valid drink types");
                    break;
                }

            }
            if (valid) break;
        }
        while (true) {
            System.out.println("What kind of flavor would you like (Coca,Sprite,pepsi)");
            String flavorInput = inputScanner.nextLine().trim();
            flavors = flavorInput.split(",");
            boolean valid = true;
            for (String flavor : flavors) {
                if (flavor.isEmpty()) {
                    valid = false;
                    System.out.println("Flavor cannot be empty. Please enter a valid drink types");
                    break;
                }
            }
            if (valid) break;
        }
        int drinkCount = Math.min(sizes.length, Math.min(types.length, flavors.length));
        for (int i = 0; i < drinkCount; i++) {
            try {
                Size size = Size.valueOf(sizes[i].trim());
                String type = types[i].trim();
                String flavor = flavors[i].trim();
                double price = PriceCalculator.getDrinkPrice(size);

                Drink drink = new Drink(size, type, flavor, price);
                order.addProduct(drink);
                System.out.printf("Added %s %s (%s) - $%.2f%n", size, type, flavor, price);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid entry please try again" + (i + 1));
            }
        }


    }

    private static void addChips() {
        System.out.println("You can add selections by using commas");
        System.out.println("What type of chips would you like? (Lays,Doritos,Kettles) for $1.50 ");
        boolean valid;
        do {
            valid = true;
            String[] BagOfChips = inputScanner.nextLine().split(",");
            for (String chips : BagOfChips) {
                String chipsName = chips.trim();
                if (chips.isEmpty()) {
                    valid = false;
                    System.out.println("chips cannot be empty. Please try again");
                    System.out.println("What type of chips would you like? (Lays,Doritos,Kettles) for $1.50 ");
                    break;
                } else {
                    BagOfChips newChips = new BagOfChips(chipsName);
                    order.addProduct(newChips);
                    System.out.println("Added chips: " + chipsName + " $" + newChips.getPrice());

                }


            }
        } while (!valid);


    }

    private static void checkout() {
        if (order == null || order.getProducts().isEmpty()) {
            System.out.println("No orders available");
            return;
        }
        System.out.println("Order Details: ");
        for (Product product : order.getProducts()) {
            System.out.println(product);
        }
        System.out.println("Total price: $" + order.getTotalPrice());
        while (true) {
            System.out.println("Would you like to checkout, cancel or edit your order?");
            System.out.println("1) Checkout 2) Cancel 3) Edit");
            String response = inputScanner.nextLine();

            if (response.equals("1")) {
                DeliFileManager.saveOrder(order);
                System.out.println("order confirmed and receipt saved");
                System.out.println("Would you like your receipt? 1)yes 2)No");
                String recieptResponse = inputScanner.nextLine();
                if(recieptResponse.equals("1")){
                    DeliFileManager.readOrder();
                }
                order = new Order();
                break;
            } else if (response.equals("2")) {
                order = new Order();
                System.out.println("order is cancelled");
                break;
            } else if (response.equals("3")) {
                System.out.println("Returning to order screen");
                orderScreen(order);
                break;

            } else {
                System.out.println("Invalid choice");
            }

        }


    }

    private static void addToppings(Sandwich sandwich) {
        while (true) {
            System.out.println("Would you like to add a topping to your sandwich?(Yes/no)");
            String response = inputScanner.nextLine();
            if (!response.equalsIgnoreCase("yes"))
                break;
            System.out.println("Select a topping 1) Meat 2) Cheese 3) Veggie ");//4) sauce 5) sides//);
            int toppingChoice;
            try {
                toppingChoice = Integer.parseInt(inputScanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                continue;
            }
            ToppingType toppingType = null;
//            String toppingName = "";
            double price = 0;
            Size size = null;
            switch (toppingChoice) {
//                toppingType = null;
                case 1:
                    toppingType = ToppingType.MEAT;
                    System.out.println("You can add selections by using commas");
                    System.out.println("Choose your Meat (Steak,Ham,salami,roast beef, chicken,bacon)");
                    String[] meats = inputScanner.nextLine().split(",");
                    for (String meat : meats) {
                        String toppingName = meat.trim();
                        if (toppingName.isEmpty()) {
                            System.out.println("Meat selection cannot be empty try again");
                            continue;
                        }
                        System.out.println("select size for the " + toppingName + " (Small (for $1.00 ), Medium (for $2.00 ), Large(for $3.00 )");
                        try {
                            size = Size.valueOf(inputScanner.nextLine().toUpperCase());
                            price = PriceCalculator.getToppingPrice(size, toppingType);
                            Topping topping = new Topping(toppingName, price, size, toppingType);
                            sandwich.addTopping(topping);
                            System.out.println("Added " + toppingName + " " + size + " " + "$" + price);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid size. please try again");
                        }
                    }
                    break;
                case 2:
                    toppingType = ToppingType.CHEESE;
                    System.out.println("You can add selections by using commas");
                    System.out.println("Choose cheese (american,provolone,cheddar,swiss)");
                    String[] cheeses = inputScanner.nextLine().split(",");
                    for (String cheese : cheeses) {
                        String toppingName = cheese.trim();
                        if (toppingName.isEmpty()) {
                            System.out.println("cheese selection cannot be empty try again");
                            continue;
                        }
                        System.out.println("select size for the " + toppingName + " (Small (for $0.75 ), Medium (for $1.50 ), Large (for $2.25 )");
                        try {
                            size = Size.valueOf(inputScanner.nextLine().toUpperCase());
                            price = PriceCalculator.getToppingPrice(size, toppingType);
                            Topping topping = new Topping(toppingName, price, size, toppingType);
                            sandwich.addTopping(topping);
                            System.out.println("Added " + toppingName + " " + size + " " + "$" + price);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid size. please try again");
                        }

                    }
                    break;
                case 3:
                    toppingType = ToppingType.VEGGIE;
                    System.out.println("You can add selections by using commas");
                    System.out.println("choose your veggies (Lettuce,peppers,onions,tomatoes,jalapenos,cucumbers,pickles,guacamole,mushrooms)");
                    String[] veggies = inputScanner.nextLine().split(",");
                    for (String veggie : veggies) {
                        String toppingName = veggie.trim();
                        if (toppingName.isEmpty()) {
                            System.out.println("veggie selection cannot be empty try again");
                            continue;
                        }
                        Topping topping = new Topping(toppingName, price, size, toppingType);
                        sandwich.addTopping(topping);
                        System.out.println("Added " + toppingName);
                    }
                    break;
//                case 4:
//                    System.out.println("choose sauce (Mayo,mustard,Ketchup,ranch,thousand islands,vinaigrette)");
//                    toppingName = inputScanner.nextLine();
//                    break;
//                case 5:
//                    System.out.println("Choose side (au jus, sauce)");
//                    toppingName = inputScanner.nextLine();
//                    break;
                default:
                    System.out.println("Invalid choice, try again");


            }
//            Topping topping;
//            if (toppingType == ToppingType.MEAT || toppingType == ToppingType.CHEESE) {
//                topping = new Topping(toppingName, price, size, toppingType);
//            } else {
//                topping = new Topping(toppingName, 0, null, toppingType);
//                System.out.println("Added " + toppingName);
//            }
//            sandwich.addTopping(topping);

        }
    }


    private static void addSauce(Sandwich sandwich) {
        while (true) {
            System.out.println("Would you like to add sauce to your order? (yes/no)");
            String response = inputScanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
            System.out.println("You can add selections by using commas");
            System.out.println("choose your sauce(mayo,mustard,ketchup,ranch,thousand islands,vinaigrette)");
            String[] sauces = inputScanner.nextLine().split(",");
            for (String sauce : sauces) {
                String sauceName = sauce.trim();
                if (sauceName.isEmpty()) {
                    System.out.println("Sauce cannot be empty. Please try again.");
                    continue;
                }
                Topping sideTopping = new Topping(sauceName, 0, null, ToppingType.VEGGIE);
                sandwich.addTopping(sideTopping);
                System.out.println("Added sauce: " + sauce);

            }

        }
    }

    private static void addSide(Sandwich sandwich) {
        while (true) {
            System.out.println("Would you like to add sides to your order?(yes/no)");
            String response = inputScanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
            System.out.println("You can add selections by using commas");
            System.out.println("choose your side (Au jus, sauce,fries)");
            String[] sides = inputScanner.nextLine().split(",");
            for (String side : sides) {
                String sideName = side.trim();
                if (sideName.isEmpty()) {
                    System.out.println("Side cannot be empty. Please try again.");
                    continue;
                }
                Topping sideTopping = new Topping(sideName, 0, null, ToppingType.VEGGIE);
                sandwich.addTopping(sideTopping);
                System.out.println("Added side: " + side);
            }

        }
    }
}
