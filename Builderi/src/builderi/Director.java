/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builderi;

/**
 *
 * @author JESSE
 */
public class Director {
    private BurgeriBuilderi builderi;
    
    public void setBurgeriBuilderi(BurgeriBuilderi builderi) {
        this.builderi = builderi;
    }
    
    public Burgeri getTheRealBurgeri() {
        return builderi.getRealBurgeri();
    }
    
    public void construcBurger() {
        builderi.createUusiBurgeri();
        builderi.buildLeipä();
        builderi.buildPihvi();
        builderi.buildJuusto();
        builderi.buildKastike();
    }
}
