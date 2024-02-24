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
public class Appointments {
    private int appointmentID;
    Customer customer;
    Services services;
    private java.util.Date date;
    private String time;

    public Appointments() {
    }

    public Appointments(int appointmentID, Customer customer, Services services, Date date, String time) {
        this.appointmentID = appointmentID;
        this.customer = customer;
        this.services = services;
        this.date = date;
        this.time = time;
    }
    
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointments{" + "appointmentID=" + appointmentID + ", customer=" + customer + ", services=" + services + ", date=" + date + ", time=" + time + '}';
    }
    
   
    
    
}
