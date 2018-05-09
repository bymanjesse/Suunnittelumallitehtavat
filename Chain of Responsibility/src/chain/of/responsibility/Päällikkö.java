/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chain.of.responsibility;

/**
 *
 * @author JESSE
 */
public class Päällikkö extends Loggeri {

   public Päällikkö(int taso){
      this.taso = taso;
   }

   @Override
   protected void kirjoita(String viesti) {		
      System.out.println("Päällikkö:  " + viesti);
   }
}
