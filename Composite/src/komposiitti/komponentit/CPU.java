package komposiitti.komponentit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author JESSE
 */
public class CPU implements Komponentti {

    private double hinta;
    
    public CPU(double hinta) {
        this.hinta = hinta;
    }
    
    @Override
    public double getHinta() {
        return hinta;
    }

    @Override
    public void lisää(Komponentti komponentti) {
        throw new UnsupportedOperationException("Tähän komponenttiin ei voi lisätä.");
    }

    @Override
    public void poista(Komponentti komponentti) {
        throw new UnsupportedOperationException("Tästä komponentistä ei voida poistaa."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Komponentti getLiitetty(int index) {
        throw new UnsupportedOperationException("Tällä komponentilla ei ole liitettyjä osia."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "prosessori";
    }
    
}
