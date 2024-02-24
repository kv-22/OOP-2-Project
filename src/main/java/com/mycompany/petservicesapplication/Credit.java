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
public class Credit extends Payments {
   private  String CardType, cardNumber, ExpirationDate,CV;
   private int idCreditPayment , idPayment;

    public Credit() {
    }

    public Credit(String CardType, String cardNumber, String ExpirationDate, String CV, int idCreditPayment, int idPayment, String payMethod, double totalAmount, Date date) {
        super(payMethod, totalAmount);
        this.CardType = CardType;
        this.cardNumber = cardNumber;
        this.ExpirationDate = ExpirationDate;
        this.CV = CV;
        this.idCreditPayment = idCreditPayment;
        this.idPayment = idPayment;
    }

       
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

   
    public String getCardType() {
        return CardType;
    }

    public void setCardType(String CardType) {
        this.CardType = CardType;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public int getIdCreditPayment() {
        return idCreditPayment;
    }

    public void setIdCreditPayment(int idCreditPayment) {
        this.idCreditPayment = idCreditPayment;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    @Override
    public String toString() {
        return "Credit{" + "CardType=" + CardType + ", ExpirationDate=" + ExpirationDate + ", idCreditPayment=" + idCreditPayment + ", idPayment=" + idPayment + '}';
    }


   
}







	

	
	
