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
public interface Visitor {
    
    public void visit(Venusaur instance);
    public void visit(Bulbasaur instance);
    public void visit(Ivysaur instance);
}
