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
public abstract class BurgeriBuilderi<T> {
    protected Burgeri burgeri;
    protected T burgeriRakenne;
    
    public T getBurger() {
        return burgeriRakenne;
    }
    
    public Burgeri getRealBurgeri() {
        return burgeri;
    }
    
    public void createUusiBurgeri() {
        burgeri = new Burgeri();
    }
    
    public abstract void buildLeipä();
    public abstract void buildPihvi();
    public abstract void buildJuusto();
    public abstract void buildKastike();
    
    @Override
    public String toString() {
        return burgeri.toString();
    }
}
