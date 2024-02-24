/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ayesh
 */
public class FoodItem extends Item {

    private String flavour;
    private String foodType;
    private Date expiryDate;
    SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FoodItem() {
    }

    public FoodItem(String flavour, Date expiryDate, String image, String itemName, int itemQuantity, double itemPrice) {
        super(itemName, itemQuantity, itemPrice, image);
        this.flavour = flavour;
        this.expiryDate = expiryDate;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String FoodItem) {
        this.foodType = FoodItem;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "foodItem{" + "flavour=" + flavour + ", FoodItem=" + foodType + ", expiryDate=YYYY-MM-DD " + sqlDateFormat.format(expiryDate) + "\n"+super.toString()+ '}';
    }

}
