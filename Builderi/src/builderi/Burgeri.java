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
public class Burgeri {
    private String leipä;
    private String pihvi;
    private String juusto;
    private String kastike;

    public void setLeipä(String leipä) {
        this.leipä = leipä;
    }

    public void setPihvi(String pihvi) {
        this.pihvi = pihvi;
    }

    public void setJuusto(String juusto) {
        this.juusto = juusto;
    }

    public void setKastike(String kastike) {
        this.kastike = kastike;
    }
    
    @Override
    public String toString() {
        return "Leipä: " + leipä + "Pihvi: " + pihvi + "Juusto: " + juusto + "Kastike: " + kastike;
    }
    
}
