/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author Ayesh
 */
public final class FileHandler {

    private static Scanner input;
    Order order;
    String customerID;
            

    public FileHandler() {

    }

    public FileHandler(Order order, String customerID) {
        this.order = order;
        this.customerID = customerID;
    }




    
    public void openFileRead() {
        try {
            input = new Scanner(Paths.get("Orders.txt"));
        } catch (IOException e) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
    
    public void closeFileRead() {
        if(input!=null)
            input.close();

    }
    
    public void readRecords() {
        openFileRead();
        try {
            while(input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (IllegalStateException e) {
            System.err.println("Error reading from file. Terminating");
            System.exit(1);
            
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        closeFileRead();
        
        
    }

    public void addRecords() {

        try {
            FileWriter fw = new FileWriter("Orders.txt", true);
            fw.write("*CUSTOMER ID* "+customerID+" ORDER ID: "+order.getOrderID()+" ORDER TOTAL: "+order.getAmount()+"\n");
            for(Item var: order.getCart()) {
                fw.write("ITEM NAME: " +var.getItemName()+ " QUANTITY: "+ var.getItemQuantity() +" PRICE: " + var.getItemPrice() +"\n");
            }
            fw.write("\n");
            fw.close();

        } catch(IOException e ) {
            System.err.println(e);
            System.exit(1);
        } catch(Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }

}
