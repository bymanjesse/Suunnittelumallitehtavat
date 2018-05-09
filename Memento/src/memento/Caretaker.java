/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memento;

/**
 *
 * @author JESSE
 */
import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private Arvaaja arvaaja;
    private Object obj;
    
    public Caretaker(Arvaaja arvaaja) {
        this.arvaaja = arvaaja;
    }
    
    public void tallennaMemento(Object obj) {
        this.obj = obj;
    }
    
    public Object getMemento() {
        return obj;
    }
    
}
