package com.codecool.plaza.cmdprog;

import com.codecool.plaza.exceptions.*;
import com.codecool.plaza.api.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private final Scanner scan;
    private Plaza plaza;
    private Shop shop;
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
    private String[] shopOptions = {"To list available products",
        "To find products by name",
        "To display the shop's owner",
        "To open the shop",
        "To close the shop",
        "To add new product to the shop",
        "To add existing products to the shop",
        "To buy a product by barcode",
        "Check price by barcode",
        "Help",
        "Go back to plaza"};
    private String[] prodTypes = {"FoodProduct", "ClothProduct"};
    private boolean plazaMenuRun = true;
    private boolean mainMenuRun = true;
    private boolean shopMenuRun = true;

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
                shopAddHandler();
                break;
            case "3":
                shopRemoveHandler();
                break;
            case "4":
                shopEntryHandler();
                break;
            case "5":
                plaza.open();
                System.out.println("Now it's Open!");
                break;
            case "6":
                plaza.close();
                System.out.println("Now it's Closed :(");
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

    private void shopEntryHandler() {
        try{
            System.out.println("Which shop you wish to visit?");
            String shopName = getInputFromUser();
            shop = plaza.findShopByName(shopName);
            System.out.printf("Welcom in the '%s'\n", shop.getName());
            listMenu(shopOptions , shop.getName());
            while(shopMenuRun){
                try{
                    handleShopMenu(getInputFromUser());
                }catch(UnsupportedOperationException e){
                    System.err.println(e);
                }
            }
            shopMenuRun = true;
        }catch(NoSuchShopException|PlazaIsClosedException e){
            System.out.println(e);
        }
    }

    public void shopListHandler(){
        try{
            System.out.println("There is your Plazas shop('s):");
            if(plaza.getShop().size()> 0){
                for(Shop shop:plaza.getShop()){
                    System.out.println(shop.getName());
                }
            }else{
                System.out.println("Sry there's no shops yet, add shops with 2. option in Plaza menu");
            }
        } catch (PlazaIsClosedException e){
            System.out.println(e);
        }
    }

    public void shopAddHandler(){
        try{
            System.out.println("What will be the name of the shop?");
            String shopName = getInputFromUser();
            System.out.println("Who is the owner of the shop");
            String owner = getInputFromUser();
            shop = new ShopImplementation(shopName, owner);
            plaza.addShop(shop);
        }catch (PlazaIsClosedException|ShopAlreadyExistException e){
            System.out.println(e);
        }
    }

    public void shopRemoveHandler(){
        System.out.println("What's the name of the shop you want to remove?");
        String shopName = getInputFromUser();
        try{
            for(Shop shop:plaza.getShop()){
                if(shop.getName().equals(shopName)){
                    plaza.removeShop(shop);
                }
            }
        }catch (NoSuchShopException|PlazaIsClosedException e){
            System.out.print(e);
        }
    }

    public void handleShopMenu(String chosenNumber) {
        switch (chosenNumber) {
            case "1":
                handleShopProducts();
                break;
            case "2":
                handleProdFinder();
                break;
            case "3":
                System.out.println("Owner of the shop is: " + shop.getOwner());
                break;
            case "4":
                shop.open();
                System.out.println("Now it's Open!!");
                break;
            case "5":
                shop.close();
                System.out.println("Now it's Closed :(");
                break;
            case "6":
                handleAddProd();
                break;
            case "7":

                break;
            case "8":

                break;
            case "9":

                break;
            case "10":
                listMenu(plazaOptions, "Plaza");
                break;
            case "11":
                shopMenuRun = false;
                listMenu(plazaOptions, "Plaza");
                break;
            case "wrong":
                System.out.println("That not a valid option, please add a new one");
                break;
            default:
                System.out.println("That not a valid option, please add a new one");
        }
    }

    public void handleShopProducts(){
        System.out.println("There is your Shop product('s)");

        try {
            if(shop.getProducts().size() > 0) {
                for (Product prod : shop.getProducts()) {
                    System.out.println(prod);
                }
            } else {
                System.out.println("Sry there's no item yet.");
            }
        } catch (ShopIsClosedException e) {
            System.out.println(e);
        } /*catch (NullPointerException ex) {
            System.out.println("Sry there's no item yet.");
        }*/
    }

    public void handleProdFinder(){
        try{
            System.out.println("What is the searched item name?");
            String itemName = getInputFromUser();
            Product prod = shop.findByName(itemName);
            System.out.println(prod.toString());
        }catch (NoSuchProductException|ShopIsClosedException e){
            System.out.println(e);
        }
    }

    public void handleAddProd(){
        try{
            System.out.println("What kind of production you wish to add?");
            int temp = 1;
            for(String t:prodTypes){
                System.out.println(temp + " " + t);
                temp++;
            }
            String chosenType = getInputFromUser();
            switch(chosenType){
                case "1":

                    break;
                case "2":
                    Random rnd = new Random();
                    int n = rnd.nextInt((9999)+1000);
                    System.out.println("What is the product name?");
                    String pname = getInputFromUser();
                    System.out.println("Who is the manufacturer");
                    String mname = getInputFromUser();
                    System.out.println("What type of production is it? ");
                    String type = getInputFromUser();
                    System.out.println("What kin of material this product made of?");
                    String material = getInputFromUser();
                    System.out.println("How much product you want to place?");
                    int quantity = Integer.parseInt(getInputFromUser());
                    System.out.println("What's the price of this product?");
                    float price = Float.parseFloat(getInputFromUser());
                    shop.addNewProduct((long)n, new ClothingProduct(pname, (long)n, mname, type, material ),quantity, price);
                    break;
                case "wrong":
                    System.out.println("That not a valid option, please add a new one");
                    break;
                default:
                    System.out.println("That not a valid option, please add a new one");

            }
        }catch(ProductAlreadyExistsException|ShopIsClosedException e){
            System.out.println(e);
        }
    }
}

