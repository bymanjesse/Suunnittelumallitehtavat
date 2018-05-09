/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JESSE
 */
public class Kompositio {
    private List<String> lista;
    private ListConverter strategia;
    
    public Kompositio(ListConverter strategia) {
        this.strategia = strategia;
        lista = new ArrayList();
    }
    
    public void addToList(String item) {
        lista.add(item);
    }
    
    public void setList(List<String> list) {
        this.lista = list;
    }
    
    public void setStrategia(ListConverter strategia) {
        this.strategia = strategia;
    }
    
    @Override
    public String toString() {
        return strategia.listToString(lista);
    }
}
