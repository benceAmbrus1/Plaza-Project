package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.PlazaImplementation;
import com.codecool.plaza.api.Product;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private final Scanner scan;
    private String[] mainOptions = {"Creat new Plaza", "Exit"};
    private String[] plazaOptions = {"To list all shops",
        "To add a new shop",
        "To remove an existing shop",
        "Enter a shop by name",
        "To open the plaza",
        "To close the plaza",
        "To check if the plaza is open or not",
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
                listMenu(mainOptions);
                handleMainMenu(getChosenNumberFromUser());
            } catch (UnsupportedOperationException e){
                System.err.println(e);
            }
        }
    }

    public void handleMainMenu(int chosenNumber){
        switch (chosenNumber) {
            case 1:
                PlazaImplementation plaza = new PlazaImplementation();
                while(plazaMenuRun){
                    try{
                        listMenu(plazaOptions);
                        handlePlazaMenu(getChosenNumberFromUser());
                    }catch(UnsupportedOperationException e){
                        System.err.println(e);
                    }
                }
                plazaMenuRun = true;
                break;
            case 2:
                mainMenuRun = false;
                break;
            case 0:
                wrongInput();
                break;
            default:
                System.out.println("That not a valid option");
        }
    }

    public int getChosenNumberFromUser(){
        try{
            return scan.nextInt();
        }catch (InputMismatchException e) {
            return 0;
        }
    }

    public void listMenu(String[] options){
        int n = 1;
        for(String option:options){
            System.out.println(n + ": " + option);
            n++;
        }
    }

    public void handlePlazaMenu(int chosenNumber){
        switch (chosenNumber) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                plazaMenuRun = false;
                break;
            case 0:
                System.out.println("That not a valid option, please add a new one");
                break;
            default:
                System.out.println("-*-");
        }
    }

    public void wrongInput(){
        System.out.println("That not a valid option, please add a new one");
    }
}
