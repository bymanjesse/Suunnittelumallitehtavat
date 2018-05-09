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
public abstract class Täyte implements Pizza {
    
    protected Pizza lisättäväTäyte;

    public Täyte(Pizza lisättäväTäyte) {
        this.lisättäväTäyte = lisättäväTäyte;
    }
    
    @Override
    public double getHinta() {
        return lisättäväTäyte.getHinta();
    }

    @Override
    public String getKuvaus() {
        return lisättäväTäyte.getKuvaus();
    }
    
}
