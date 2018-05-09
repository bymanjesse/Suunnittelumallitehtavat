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
public class Bulbasaur implements PokemonState {

    private static Bulbasaur instance = null;
    
    private Bulbasaur() {
    
    }
    
    public static PokemonState getInstance() {
        if(instance == null) {
            instance = new Bulbasaur();
        }
        return instance;
    }
    

    @Override
    public void move() {
        System.out.println("Liikkuu todella hitaasti");
    }

    @Override
    public void fight() {
        System.out.println("Taistelee todella heikosti");
    }

    @Override
    public void talk() {
        System.out.println("'Bulba bulba'");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(instance);
    }
    
}
