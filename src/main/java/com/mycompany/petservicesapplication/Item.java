/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

/**
 *
 * @author Kavya
 */
public class Item {

    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private String petType;
    private String image;

    public Item() {
    }

    public Item(String itemName, int itemQuantity, double itemPrice, String image) {
        
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.image = image;
    }



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + ", itemQuantity=" + itemQuantity + ", itemPrice=" + itemPrice + ", petType=" + petType + ", image=" + image + '}';
    }


    
    
    
    


    
    
    
}
