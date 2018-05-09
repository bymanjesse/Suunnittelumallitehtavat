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
public class Ivysaur implements PokemonState {

    private static Ivysaur instance = null;
    
    private Ivysaur() {
    
    }
    
    public static PokemonState getInstance() {
        if(instance == null) {
            instance = new Ivysaur();
        }
        return instance;
    }

    @Override
    public void move() {
        System.out.println("Liikku nopeasti");
    }

    @Override
    public void fight() {
        System.out.println("Taistelee hyvin");
    }

    @Override
    public void talk() {
        System.out.println("'Ivy ivy'");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(instance);
    }
    
}
