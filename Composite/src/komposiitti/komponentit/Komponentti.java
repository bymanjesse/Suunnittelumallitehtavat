package komposiitti.komponentit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JESSE
 */
public interface Komponentti {
    
    public abstract double getHinta();
    public abstract void lisää(Komponentti komponentti);
    public abstract void poista(Komponentti komponentti);
    public abstract Komponentti getLiitetty(int index);
    @Override
    public abstract String toString();
}
