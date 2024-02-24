/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

/**
 *
 * @author Ayesh
 */
public class Pet {
    private String petType, petName;
    private int age;

    public Pet() {
    }

    public Pet(String petType, String petName, int age) {
        this.petType = petType;
        this.petName = petName;
        this.age = age;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" + "petType=" + petType + ", petName=" + petName + ", age=" + age + '}';
    }
    
    
}

