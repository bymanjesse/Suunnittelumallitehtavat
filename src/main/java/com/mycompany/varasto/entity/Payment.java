/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.entity;

/**
 *
 * @author aleks
 */
public class Payment {
    
    // maksu luokka ei hibernatea koska kaikki tieto saadaan muualta tietokannasta
    
    private double subTotal;
    private double vat;
    private double discount;
    private double payable;

    public Payment(double subTotal, double vat, double discount, double payable) {
        this.subTotal = subTotal;
        this.vat = vat;
        this.discount = discount;
        this.payable = payable;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }
    
    
    
}
