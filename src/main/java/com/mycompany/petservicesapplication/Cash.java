/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.util.Date;

/**
 *
 * @author Ayesh
 */
public class Cash extends Payments {
    private double amountPayed,balance;

    public Cash() {
    }

   
    public Cash(double amountPayed, double balance, String payMethod, double totalAmount, Date date) {
        super(payMethod, totalAmount);
        this.amountPayed = amountPayed;
        this.balance = balance;
    }

    public double getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(double amountPayed) {
        this.amountPayed = amountPayed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Cash{" + "amountPayed=" + amountPayed + ", balance=" + balance + '}';
    }

    
    
    
}
