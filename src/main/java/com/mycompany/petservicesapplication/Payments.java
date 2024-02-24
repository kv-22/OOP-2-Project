package com.mycompany.petservicesapplication;

import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Juman
 */
public class Payments {

    private String payMethod;
    private final double TAX = 0.15;
    private double totalAmount;
    private java.util.Date date;

    public Payments(String payMethod, double totalAmount) {
        this.payMethod = payMethod;
        this.totalAmount = totalAmount;
        setDate(new Date());
    }

    public Payments() {
    }

    public String getPayMethod() {
        return payMethod;
    }

    public double getTAX() {
        return TAX;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setTotalAmount(double amount) {
        this.totalAmount = amount;
    }
    
        public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payments{" + "payMethod=" + payMethod + ", TAX=" + TAX + ", totalAmount=" + totalAmount + ", date=" + date + '}';
    }
    


}
