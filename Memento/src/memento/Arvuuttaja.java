/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author JESSE
 */
public class Arvuuttaja {
    
    public boolean arvaus(Arvaaja pelaaja, int arvaus) {
        if( pelaaja.getOikeaNumero() == arvaus) {
            return true;
        }
        else return false;
    
    }
    
    public static void liityPeliin(Arvaaja pelaaja) {
        int arvattavaNumero = ThreadLocalRandom.current().nextInt(0, 101);
        pelaaja.setMemento(pelaaja.tallennaOikeaNumero(arvattavaNumero));
        
    }
    
}
