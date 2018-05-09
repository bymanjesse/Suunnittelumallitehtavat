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
public class Arvaaja implements Runnable{
    private boolean arvausOikein = false;
    private Arvuuttaja peli;
    private Caretaker caretaker;
    
    
    public Arvaaja(Arvuuttaja peli) {
        this.peli = peli;
        caretaker = new Caretaker(this);
    }

    @Override
    public void run() {
        peli.liityPeliin(this);
        while(!arvausOikein) {
            try {
                int guess = ThreadLocalRandom.current().nextInt(0, 101);
                arvausOikein = peli.arvaus(this, guess);
                if(arvausOikein) {
                    System.out.println("Pelaaja arvasi oikein! Luku oli " + String.valueOf(guess));
                } 
                Thread.sleep(10);
            
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public Memento tallennaOikeaNumero(int correctNum) {
        return new Memento(correctNum);
    }
    
    public int getOikeaNumero() {
        Memento memento = (Memento) caretaker.getMemento();
        return memento.getOikeaNum();
    }
    
    public void setMemento(Object obj) {
        caretaker.tallennaMemento(obj);
    }
    
    
    private static class Memento {
    
        private final int oikeaNum;
        
        public Memento(int oikeaNum) {
            this.oikeaNum = oikeaNum;
        }
        
        private int getOikeaNum() {
            return this.oikeaNum;
        }
    }
}
