/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractfactory;

/**
 *
 * @author JESSE
 */
public class FactoryProducer {
   public static AbstractFactory getFactory(String choice){
   
      if(choice.equalsIgnoreCase("ADIDAS")){
         return new AdidasFactory();
         
      }else if(choice.equalsIgnoreCase("BOSS")){
         return new BossFactory();
      }
      
      return null;
   }
}
