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
public class Pohja implements Pizza {
    private double price = 4.50;

    @Override
    public double getHinta() {
        return price;
    }

    @Override
    public String getKuvaus() {
        return "tomaattikastike vegaanijuusto";
    }
    
}
