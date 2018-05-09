/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 *
 * @author JESSE
 */
public class KomentoYlös implements Komento{

    private Valkokangas valkokangas;
    
    public KomentoYlös(Valkokangas valkokangas) {
        this.valkokangas = valkokangas;
    }
    
    @Override
    public void toiminto() {
        valkokangas.ylös();
    }
    
    
    
}
