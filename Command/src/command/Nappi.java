/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 *
 * @author JESSE
 */
public class Nappi {
    private Komento komento;
    
    public Nappi(Komento komento) {
        this.komento = komento;
    }
    
    public void paina() {
        komento.toiminto();
    }
}
