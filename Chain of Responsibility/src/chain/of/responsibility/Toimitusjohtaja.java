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
public class Toimitusjohtaja extends Loggeri {

   public Toimitusjohtaja(int taso){
      this.taso = taso;
   }

   @Override
   protected void kirjoita(String viesti) {		
      System.out.println("Toimitusjohtaja: " + viesti);
   }
}
