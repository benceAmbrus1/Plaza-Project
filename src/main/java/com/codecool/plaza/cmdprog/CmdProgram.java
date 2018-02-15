package com.codecool.plaza.cmdprog;

import com.codecool.plaza.Exceptions.PlazaIsClosedException;
import com.codecool.plaza.api.Plaza;
import com.codecool.plaza.api.PlazaImplementation;
import com.codecool.plaza.api.Product;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private final Scanner scan;
    private Plaza plaza;
    private String[] mainOptions = {"Creat new Plaza", "Exit"};
    private String[] plazaOptions = {"To list all shops",
        "To add a new shop",
        "To remove an existing shop",
        "Enter a shop by name",
        "To open the plaza",
        "To close the plaza",
        "To check if the plaza is open or not",
        "Help",
        "Leave plaza"};
    private boolean plazaMenuRun = true;
    private boolean mainMenuRun = true;

    public CmdProgram(){
        scan = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Welcome in the Plaza creator.");
        while(mainMenuRun) {
            try {
                listMenu(mainOptions, "Main");
                handleMainMenu(getInputFromUser());
            } catch (UnsupportedOperationException e){
                System.err.println(e);
            }
        }
    }

    public void handleMainMenu(String chosenNumber){
        switch (chosenNumber) {
            case "1":
                plazaHandler();
                break;
            case "2":
                mainMenuRun = false;
                break;
            case "wrong":
                System.out.println("That not a valid option, please add a new one");
                break;
            default:
                System.out.println("That not a valid option, please add a new one");
        }
    }

    public void plazaHandler(){
        System.out.println("What should be your new plaza name?");
        String pn = getInputFromUser();
        plaza = new PlazaImplementation(pn);
        System.out.printf("Welcom in the '%s'\n", pn);
        listMenu(plazaOptions , "Plaza");
        while(plazaMenuRun){
            try{
                handlePlazaMenu(getInputFromUser());
            }catch(UnsupportedOperationException e){
                System.err.println(e);
            }
        }
        plazaMenuRun = true;
    }

    public String getInputFromUser(){
        try{
            return scan.nextLine();
        }catch (Exception e) {
            return "wrong";
        }
    }

    public void listMenu(String[] options, String menuName){
        System.out.println(menuName + " menu:");
        int n = 1;
        for(String option:options){
            System.out.println(n + ": " + option);
            n++;
        }
    }

    public void handlePlazaMenu(String chosenNumber){
        switch (chosenNumber) {
            case "1":
                shopListHandler();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                plaza.open();
                break;
            case "6":
                plaza.close();
                break;
            case "7":
                if(plaza.isOpen()){
                    System.out.println("It's Open!!");
                } else {
                    System.out.println("Sry it's Closed");
                }
                break;
            case "8":
                listMenu(plazaOptions, "Plaza");
                break;
            case "9":
                plazaMenuRun = false;
                break;
            case "wrong":
                System.out.println("That not a valid option, please add a new one");
                break;
            default:
                System.out.println("That not a valid option, please add a new one");
        }
    }

    public void shopListHandler(){
        try{
            int size = plaza.getShop().size();
            if(size > 0){
                plaza.getShop();
            }else{
                System.out.println("Sry there's no shops yet, add shops with 2. option in Plaza menu");
            }
        } catch (PlazaIsClosedException e){
            System.out.println(e);
        }
    }
}
