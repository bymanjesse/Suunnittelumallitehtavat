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
public class EsimiesTesti {
	
   private static Loggeri getLoggeriKetju(){

      Loggeri päällikkö = new Päällikkö(Loggeri.Päällikkö);
      Loggeri toimitusjohtaja = new Toimitusjohtaja(Loggeri.Toimitusjohtaja);
      Loggeri lähiEsimies = new Lähiesimies(Loggeri.Lähiesimies);

      lähiEsimies.setSeuraavaEsimies(päällikkö);
      päällikkö.setSeuraavaEsimies(toimitusjohtaja);

      return lähiEsimies;	
   }

   public static void main(String[] args) {
      Loggeri loggeriKetju = getLoggeriKetju();

      loggeriKetju.loggeriViesti(Loggeri.Lähiesimies, 
         "2 prosentin korotus.");
       System.out.println("\n");
       
      loggeriKetju.loggeriViesti(Loggeri.Päällikkö, 
         "2-5 prosentin korotus.");
       System.out.println("\n");
      
      loggeriKetju.loggeriViesti(Loggeri.Toimitusjohtaja, 
         "Yli 5 prosentin korotus.");
      System.out.println("\n");
   }
}
