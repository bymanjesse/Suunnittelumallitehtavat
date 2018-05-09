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
public class PokemonContext implements PokemonState {
    private PokemonState state;
    private int stateIndex;
    
    public PokemonContext() {
        stateIndex = 0;
        state = Bulbasaur.getInstance();
    }
    
    public void evolve() {
        switch(stateIndex) {
            case 0:
                state = Ivysaur.getInstance();
                stateIndex++;
                break;
            case 1:
                state = Venusaur.getInstance();
                stateIndex++;
                break;
            case 2:
                System.out.println("Pokemonisi ei voi kehittyä enää pidemmälle.");
                break;
        }
    }
    
    public void devolve() {
        switch(stateIndex) {
            case 0:
                System.out.println("Pokemonisi ei voi kehittyä enempää taaksepäin.");
                break;
            case 1:
                state = Bulbasaur.getInstance();
                stateIndex--;
                break;
            case 2: 
                state = Ivysaur.getInstance();
                stateIndex--;
                break;
        }
    }
    
    @Override
    public void move() {
        state.move();
    }
    
    @Override
    public void fight() {
        state.fight();
    }
    
    @Override
    public void talk() {
        state.talk();
    }

    @Override
    public void accept(Visitor visitor) {
        state.accept(visitor);
    }
    
}
