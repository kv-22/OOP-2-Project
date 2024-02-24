/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.util.ArrayList;

/**
 *
 * @author fatim
 */
public class Customer extends User {

    private String name;
    private String phone;
    private int reward_points;
    private String petType;
    private ArrayList<Item> cart;
    private ArrayList<Integer> quantities;
    //ArrayList<Order> order;

    public Customer(String name, String phone, int Reward_points, String userID,
            String Password, String userType, String userEmail) {
        super(userID, Password, "Customer", userEmail);
        this.name = name;
        this.phone = phone;
        this.reward_points = Reward_points;
        cart = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public Customer() {
    }

    public ArrayList<Item> getCart() {
        return cart;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    public void addToCart(Item item, int quantity) {
        int index = searchCart(item);
        if (index < 0) {
            cart.add(item);
            quantities.add(quantity);
        } else {
            int old = quantities.get(index);
            int maxQuant = cart.get(index).getItemQuantity();
            int newQuant = old + quantity;
            if (newQuant > maxQuant) {
                quantities.set(index, maxQuant);
            } else {
                quantities.set(index, newQuant);
            }
        }
    }

    private int searchCart(Item searchableItem) {
        int index = -1, i = 0;
        for (Item item : cart) {
            if (item.getItemName().equalsIgnoreCase(searchableItem.getItemName())) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

    public void removeFromCart(Item item) {
        int index = searchCart(item);
        cart.remove(index);
        quantities.remove(index);
    }

    public void clearCart() {
        cart.clear();
        quantities.clear();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getReward_points() {
        return reward_points;
    }

    public void setReward_points(int Reward_points) {
        this.reward_points = Reward_points;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", phone=" + phone + ", Reward_points=" + reward_points + '}';
    }

}
