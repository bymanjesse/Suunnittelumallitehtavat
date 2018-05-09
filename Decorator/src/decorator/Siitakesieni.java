/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

/**
 *
 * @author JESSE
 */
public class Siitakesieni extends Täyte {
    
    private double price;
    
    public Siitakesieni(Pizza lisättäväTäyte) {
        super(lisättäväTäyte);
        price = 3.50;
    }

    @Override
    public String getKuvaus() {
        return super.getKuvaus() + " siitakesieni"; 
    }

    @Override
    public double getHinta() {
        return super.getHinta() + price; 
    }
    
    
}

