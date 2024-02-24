/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

/**
 *
 * @author Ayesh
 */
public class Grooming extends Services {
    
    private String petType, packageType;


    
    public Grooming() {}



    public Grooming(String petType, String packageType, int serviceID, String serviceName, double servicePrice) {
        super(serviceID, serviceName, servicePrice);
        this.petType = petType;
        this.packageType = packageType;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    @Override
    public String toString() {
        return "grooming{" + "petType=" + petType + ", packageType=" + packageType + '}';
    }
    
    
    
        
}
