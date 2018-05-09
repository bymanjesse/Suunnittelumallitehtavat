/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

/**
 *
 * @author JESSE
 */
public class Venusaur implements PokemonState {
    private static Venusaur instance = null;
    
    
    
    public static PokemonState getInstance() {
    if(instance == null) {
            instance = new Venusaur();
        }
        return instance;
    }

    @Override
    public void move() {
        System.out.println("Liikkuu todella nopeasti");
    }

    @Override
    public void fight() {
        System.out.println("Taistelee todella voimakkaasti");
    }

    @Override
    public void talk() {
        System.out.println("'Veny veny'");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(instance);
    }
    
}
