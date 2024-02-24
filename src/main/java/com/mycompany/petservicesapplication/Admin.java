
package com.mycompany.petservicesapplication;

public class Admin extends User{
    
    private final String adPassword = "Admin12";
    private final int adID = 1234;
    private final String adEmail = "Admin12@gmail.com";
    
    public Admin(){}

    public String getAdPassword() {
        return adPassword;
    }

    public int getAdID() {
        return adID;
    }

    public String getAdEmail() {
        return adEmail;
    }   
    
}
