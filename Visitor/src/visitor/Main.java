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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PokemonContext pokemon = new PokemonContext();
        Visitor bonus = new VisitorBonus();
        
        for(int i = 0; i < 8; i++) {
            pokemon.talk();
            pokemon.move();
            pokemon.fight();
            pokemon.accept(bonus);
            System.out.println("");
            if(i < 2) {
                pokemon.evolve();
            }
            else if(i < 5) {
                pokemon.devolve();
            }
            else pokemon.evolve();
        }
    }
    
}
