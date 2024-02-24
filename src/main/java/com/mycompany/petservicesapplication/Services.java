/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

/**
 *
 * @author fatim
 */
public class Services {
    
    int serviceID;
    String serviceName;
    double servicePrice; 
    
    public Services(){
        
    }

    public Services(int serviceID, String serviceName, double servicePrice) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }
    
    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        return "Services{" + "serviceID=" + serviceID + ", serviceName=" + serviceName + ", servicePrice=" + servicePrice + '}';
    }
    
    
     
}
