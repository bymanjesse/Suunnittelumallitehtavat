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
public abstract class Loggeri {
   public static int Lähiesimies = 1;
   public static int Päällikkö = 2;
   public static int Toimitusjohtaja = 3;

   protected int taso;

   //seuraava esimies jonossa
   protected Loggeri seuraavaEsimies;

   public void setSeuraavaEsimies(Loggeri seuraavaEsimies){
      this.seuraavaEsimies = seuraavaEsimies;
   }

   
   //loggeri menee eteenpäin niin kauan että se löytää esimiehen jolla on oikeudet
   //printtaa myös viesti niin kauan että se osuu oikealle esimiehelle
   public void loggeriViesti(int taso, String viesti){
      if(this.taso <= taso){
         kirjoita(viesti);
      }
      if(seuraavaEsimies !=null){
         seuraavaEsimies.loggeriViesti(taso, viesti);
      }
   }

   abstract protected void kirjoita(String message);
	
}
