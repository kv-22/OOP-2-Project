/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

/**
 *
 * @author zahja
 */
public class Vet extends Services {
    
    private String vetServiceType;
    private String doctorName;
    
            
    public Vet ( String vetServiceType, String doctorName, int serviceID, String serviceName, double servicePrice){
        super(serviceID,serviceName,servicePrice);
        this.vetServiceType = vetServiceType;
        this.doctorName = doctorName;
    }

    public String getVetServiceType() {
        return vetServiceType;
    }

    public void setVetServiceType(String vetServiceType) {
        this.vetServiceType = vetServiceType;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "Vet{" + "vetServiceType=" + vetServiceType + ", doctorName=" + doctorName + '}';
    }
    
    
    

    
}

