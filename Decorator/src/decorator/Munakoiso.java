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
public class Munakoiso extends Täyte {
    
    private double price;
    
    public Munakoiso(Pizza lisättäväTäyte) {
        super(lisättäväTäyte);
        price = 2.50;
    }

    @Override
    public String getKuvaus() {
        return super.getKuvaus() + " munakoiso"; 
    }

    @Override
    public double getHinta() {
        return super.getHinta() + price; 
    }
    
    
}
