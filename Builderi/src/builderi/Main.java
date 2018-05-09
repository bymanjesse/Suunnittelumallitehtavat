/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builderi;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JESSE
 */
public class Main {
    
    public static void main(String[] args) {
        BurgeriBuilderi mäkkäri = new MäkkiBuilderi();
        BurgeriBuilderi hese = new HeseBuilderi();
        Director kokki = new Director();
        
        kokki.setBurgeriBuilderi(mäkkäri);
        kokki.construcBurger();
        Burgeri Kasvishamppari = kokki.getTheRealBurgeri();
        List mcStructure = (ArrayList) mäkkäri.getBurger();
        
        kokki.setBurgeriBuilderi(hese);
        kokki.construcBurger();
        Burgeri Ruishamppari = kokki.getTheRealBurgeri();
        StringBuilder heseStructure = (StringBuilder) hese.getBurger();
        
        System.out.println("McDonalds: ");
        System.out.println(Kasvishamppari.toString());
        System.out.println(mcStructure.toString());
        
        System.out.println("Hesburger: ");
        System.out.println(Ruishamppari.toString());
        System.out.println(heseStructure.toString());
    }
   

}
