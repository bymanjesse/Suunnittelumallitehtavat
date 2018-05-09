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
public class HeseBuilderi extends BurgeriBuilderi<StringBuilder>{

    public HeseBuilderi() {
        burgeriRakenne = new StringBuilder();
    }
    
    @Override
    public void buildLeipä() {
        Leipä leipä = new Leipä("Ruis ");
        burgeriRakenne.append(leipä.getLeipä());
        burgeri.setLeipä(leipä.getLeipä());
    }

    @Override
    public void buildPihvi() {
        Pihvi pihvi = new Pihvi("kanapihvi ");
        burgeriRakenne.append(pihvi.getPihvi());
        burgeri.setPihvi(pihvi.getPihvi());
    }

    @Override
    public void buildJuusto() {
        Juusto juusto = new Juusto("cheddari ");
        burgeriRakenne.append(juusto.getJuusto());
        burgeri.setJuusto(juusto.getJuusto());
    }

    @Override
    public void buildKastike() {
        Kastike kastike = new Kastike("talonkastike ");
        burgeriRakenne.append(kastike.getKastike());
        burgeri.setKastike(kastike.getKastike());
    }
}

    

