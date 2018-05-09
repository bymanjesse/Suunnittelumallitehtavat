/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JESSE
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        List<String> list = new ArrayList();
        ListConverter strategia_1 = new Strategia_1();
        Kompositio context = new Kompositio(strategia_1);
        String testiString = "The most difficult thing is the decision to act, the rest is merely tenacity. The fears are paper tigers. You can do anything you decide to do. You can act to change and control your life; and the procedure, the process is its own reward.";
        
        for(String snippet : testiString.split(" ")) {
            list.add(snippet);
        }
        
        context.setList(list);
        
        System.out.println(context.toString());
        
        context.setStrategia(new Strategia_2());
        System.out.println("\n" + context.toString());
        
        context.setStrategia(new Strategia_3());
        System.out.println("\n" + context.toString());
    }
    
}
