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
public class VisitorBonus implements Visitor{

    @Override
    public void visit(Venusaur instance) {
        System.out.println("Suuri bonus");
    }

    @Override
    public void visit(Bulbasaur instance) {
        System.out.println("Pieni bonus");
    }

    @Override
    public void visit(Ivysaur instance) {
        System.out.println("Keskiverto");
    }
    
}
