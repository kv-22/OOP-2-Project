/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ayesh
 */
public class Order {
    
private int orderID;
private double amount;
ArrayList<Item> cart;


    public Order() {
    }

    public Order(int orderID, double amount, ArrayList<Item> cart) {
        this.orderID = orderID;
        this.amount = amount;
        this.cart = cart;
        
    }

    public void placeOrder(){
    }
    
    public ArrayList<Item> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Item> cart) {
        this.cart = cart;
    }
        
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", amount=" + amount + ", cart=" + cart + '}';
    }
 
    
}
