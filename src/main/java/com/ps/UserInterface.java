package com.ps;

import com.ps.customClasses.Order;
import com.ps.customClasses.enums.BreadType;
import com.ps.customClasses.enums.Size;
import com.ps.customClasses.sandwiches.Sandwich;

import java.util.Scanner;

public class UserInterface {
    private static Scanner commandScanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
    private static Sandwich sandwich = new Sandwich();
    private static Order order = new Order();
    public static void display(){
        init();
    }

    public static void init(){
        int mainMenuCommand;
        do{
            System.out.println("1) New order");
            System.out.println("0) Exit ");
            mainMenuCommand = commandScanner.nextInt();
            switch(mainMenuCommand){
                case 1:
                    orderScreen();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid command please try again");
            }
        }while(mainMenuCommand != 0);


    }
    private static void orderScreen(){
        int orderScreenCommand;
        do{
            System.out.println("1) Add sandwich");
            System.out.println("2) Add drink");
            System.out.println("3) Add chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel order");
            orderScreenCommand = commandScanner.nextInt();
            switch(orderScreenCommand){
                case 1:
                    //addSandwich();
                    break;
                case 2:
                    //addDrink();
                    break;
                case 3:
                    //addChips();
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

        }while(orderScreenCommand != 0);
    }

    private static void addSandwich(){
    }

    private static void addDrink(){
    }

    private static void addChips(){
    }

    private static void checkout(){
    }
}
