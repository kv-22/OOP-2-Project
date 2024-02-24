/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

/**
 *
 * @author zahja
 */
public class User {
    
   private String userID;
   private String Password; 
   private String userType;
   private String userEmail;


    public User(String userID, String Password, String userType, String userEmail) {
        this.userID = userID;
        this.Password = Password;
        this.userType = userType;
        this.userEmail = userEmail;
        
    }
    
    public User() {
        
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", Password=" + Password + ", userType=" + userType + ", userEmail=" + userEmail + '}';
    }
}
    
   

 
    
  
 