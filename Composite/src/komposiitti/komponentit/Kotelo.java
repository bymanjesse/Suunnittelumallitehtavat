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
public class Kotelo implements Komponentti{

    private ArrayList<Komponentti> komponentit;
    private double hinta;
    
    public Kotelo(double hinta) {
        this.hinta = hinta;
        komponentit = new ArrayList();
    }
    
    @Override
    public double getHinta() {
        double sum = hinta;
        for(Komponentti komponentti : komponentit) {
            sum += komponentti.getHinta();
        }
        return sum;
    }

    @Override
    public void lisää(Komponentti komponentti) {
        komponentit.add(komponentti);
    }

    @Override
    public void poista(Komponentti komponentti) {
        komponentit.remove(komponentit.indexOf(komponentti));
    }

    @Override
    public Komponentti getLiitetty(int index) {
        return komponentit.get(index);
    }
    
    @Override
    public String toString() {
        return "kotelo";
    }
    
}
