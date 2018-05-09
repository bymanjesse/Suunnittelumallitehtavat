/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author JESSE
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pizza Vegan1 = new Munakoiso(new Siitakesieni(new Pohja()));
        Pizza Vegan2 = new Munakoiso(new Sipuli(new Pohja()));
        Pizza Vegan3 = new Avokado(new Siitakesieni(new Sipuli(new Pohja())));
        
        Map<String, Object> menu = new HashMap();
        
        menu.put("Vegano Munakoiso", Vegan1);
        menu.put("Vegano Sipuli", Vegan2);
        menu.put("Vegano Avokado", Vegan3);
        
        for(Map.Entry<String, Object> item : menu.entrySet()) {
            String name = item.getKey();
            Pizza pizza = (Pizza) item.getValue();
            
            System.out.println(name + ": " + pizza.getKuvaus() + " " + String.valueOf(pizza.getHinta()) + " euroa.");
        }
    
    }
    
}
