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
public class Main {
    
    public static void main(String[] args) {
        Valkokangas valkokangas = new Valkokangas();
        Komento ylös = new KomentoYlös(valkokangas);
        Komento alas = new KomentoAlas(valkokangas);
        Nappi upButton = new Nappi(ylös);
        Nappi downButton = new Nappi(alas);
        
        downButton.paina();
        upButton.paina();
    }
    
}