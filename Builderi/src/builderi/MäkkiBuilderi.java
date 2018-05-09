/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builderi;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JESSE
 */
public class MäkkiBuilderi extends BurgeriBuilderi<List>{
    
    public MäkkiBuilderi() {
        burgeriRakenne = new ArrayList();
    }

    @Override
    public void buildLeipä() {
        Leipä leipä = new Leipä("Seesaminsiemen leipä ");
        burgeriRakenne.add(leipä);
        burgeri.setLeipä(leipä.getLeipä());
    }

    @Override
    public void buildPihvi() {
        Pihvi pihvi = new Pihvi("kasvispihvi ");
        burgeriRakenne.add(pihvi);
        burgeri.setPihvi(pihvi.getPihvi());
        
    }

    @Override
    public void buildJuusto() {
        Juusto juusto = new Juusto("gouda ");
        burgeriRakenne.add(juusto);
        burgeri.setJuusto(juusto.getJuusto());
    }

    @Override
    public void buildKastike() {
        Kastike kastike = new Kastike("ketsuppi ");
        burgeriRakenne.add(kastike);
        burgeri.setKastike(kastike.getKastike());
    }
    
}
