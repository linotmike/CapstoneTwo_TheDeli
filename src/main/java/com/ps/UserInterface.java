package com.ps;

import com.ps.customClasses.OtherProducts.BagOfChips;
import com.ps.customClasses.OtherProducts.Drink;
import com.ps.customClasses.Order;
import com.ps.customClasses.Helper.PriceCalculator;
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
    private static Order order = new Order();

    public static void display() {
        init();
    }

    public static void init() {
        int mainMenuCommand;
        do {
            System.out.println("1) New order");
            System.out.println("0) Exit ");
            mainMenuCommand = commandScanner.nextInt();
            switch (mainMenuCommand) {
                case 1:
                    orderScreen();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid command please try again");
            }
        } while (mainMenuCommand != 0);


    }

    private static void orderScreen() {
        int orderScreenCommand;
        do {
            System.out.println("1) Add sandwich");
            System.out.println("2) Add drink");
            System.out.println("3) Add chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel order");
            orderScreenCommand = commandScanner.nextInt();
            switch (orderScreenCommand) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    //checkout();
                    break;
                case 0:
                    //cancelOrder();
                    break;
                default:
                    System.out.println("Invalid command please try again");

            }

        } while (orderScreenCommand != 0);
    }

    private static void addSandwich() {
        System.out.println("What is your name?");
        String name = inputScanner.nextLine();
        System.out.println("Select your bread (Wheat,White,Rye,Wrap)");
        String bread = inputScanner.nextLine().toUpperCase();
        BreadType breadType = BreadType.valueOf(bread);
        System.out.println("Select the size of your sandwich small(4in), medium(8in), Large(12in)");
        String sizeInput = inputScanner.nextLine().toUpperCase();
        Size size = Size.valueOf(sizeInput);
        System.out.println("Would you like your sandwich to be toasted? (Yes/No)");
        boolean isToasted = inputScanner.nextLine().equalsIgnoreCase("Yes");
        Sandwich sandwich = new Sandwich(name, size, breadType, isToasted);
        addToppings(sandwich);
        addSauce(sandwich);
        addSide(sandwich);
        System.out.println("sandwich added successfully");
//        System.out.print("Name " + name + " size " + size + " bread type " + breadType + isToasted + "Sandwich" + sandwich);
        System.out.println(sandwich);

    }

    private static void addDrink() {
        System.out.println("Choose your drink size (Small ,Medium, Large)");
        String drinkSize = inputScanner.nextLine().toUpperCase();
        Size size = Size.valueOf(drinkSize);
        System.out.println("What kind of drink would you like (Soda,Water,Juice)");
        String type = inputScanner.nextLine();
        System.out.println("What kind of flavor would you like (Coca,Sprite,pepsi)");
        String flavor = inputScanner.nextLine();
        double price = PriceCalculator.getDrinkPrice(size);

        Drink drink = new Drink(size,type,flavor,price);
        System.out.printf("Added %s %s (%s) - $%.2f%n", size, type, flavor, price);


    }

    private static void addChips() {
        System.out.println("What type of chips would you like? (Lays,Doritos,Kettles)");
        String type = inputScanner.nextLine();

        BagOfChips chips = new BagOfChips(type);
        System.out.println("Added chips: " + type + " $" + chips.getPrice());
    }

    private static void checkout() {
    }

    private static void addToppings(Sandwich sandwich) {
        while (true) {
            System.out.println("Would you like to add a topping to your sandwich?(Yes/no)");
            String response = inputScanner.nextLine();
            if (!response.equalsIgnoreCase("yes"))
                break;
            System.out.println("Select a topping 1) Meat 2) Cheese 3) Veggie ");//4) sauce 5) sides//);
            int toppingChoice = Integer.parseInt(inputScanner.nextLine());
            ToppingType toppingType = null;
            String toppingName = "";
            double price = 0;
            Size size = null;
            switch (toppingChoice) {
//                toppingType = null;
                case 1:
                    toppingType = ToppingType.MEAT;
                    System.out.println("Choose Meat (Steak,Ham,salami,roast beef, chicken,bacon)");
                    toppingName = inputScanner.nextLine();
                    System.out.println("select size for the meat(Small,Medium,Large)");
                    size = Size.valueOf(inputScanner.nextLine().toUpperCase());
                    price = PriceCalculator.getToppingPrice(size, toppingType);
                    break;
                case 2:
                    toppingType = ToppingType.CHEESE;
                    System.out.println("Choose cheese (american,provolone,cheddar,swiss)");
                    toppingName = inputScanner.nextLine();
                    System.out.println("select size for the cheese (Small,Medium,Large)");
                    size = Size.valueOf(inputScanner.nextLine().toUpperCase());
                    price = PriceCalculator.getToppingPrice(size, toppingType);
                    break;
                case 3:
                    toppingType = ToppingType.VEGGIETOPPING;
                    System.out.println("choose your veggies (Lettuce,peppers,onions,tomatoes,jalapenos,cucumbers,pickles,guacamole,mushrooms)");
                    toppingName = inputScanner.nextLine();
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
            Topping topping;
            if (toppingType == ToppingType.MEAT || toppingType == ToppingType.CHEESE) {
                topping = new Topping(toppingName, price, size, toppingType);
                System.out.println("Added " + toppingName + " " + size + " " + "$" + price);
            } else {
                topping = new Topping(toppingName, 0, null, toppingType);
                System.out.println("Added " + toppingName);
            }
            sandwich.addTopping(topping);

        }
    }

    private static void addSauce(Sandwich sandwich) {
        while (true) {
            System.out.println("Would you like to add sauce to your order? (yes/no)");
            String response = inputScanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
            System.out.println("choose your sauce(mayo,mustard,ketchup,ranch,thousand islands,vinaigrette)");
            String sauce = inputScanner.nextLine();

            Topping sideTopping = new Topping(sauce, 0, null, ToppingType.VEGGIETOPPING);
            sandwich.addTopping(sideTopping);
            System.out.println("Added sauce: " + sauce);
        }
    }

    private static void addSide(Sandwich sandwich) {
        while (true) {
            System.out.println("Would you like to add sides to your order?(yes/no)");
            String response = inputScanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
            System.out.println("choose your side (Au jus, sauce,fries)");
            String side = inputScanner.nextLine();
            Topping sideTopping = new Topping(side,0,null,ToppingType.VEGGIETOPPING);
            sandwich.addTopping(sideTopping);
            System.out.println("Added side: " + side);

        }
    }
}
