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
public class BossFactory extends AbstractFactory {
	
   
   @Override
   Jasper getVaate(String vaate) {
   
      if(vaate == null){
         return null;
      }		
      
      if(vaate.equalsIgnoreCase("HOUSUT")){
         return new BossHousut();
         
      }else if(vaate.equalsIgnoreCase("PAITA")){
         return new BossPaita();
         
      }else if(vaate.equalsIgnoreCase("LIPPIS")){
         return new BossLippis();
                 
      }else if(vaate.equalsIgnoreCase("KENGÄT")){
         return new BossKengät();
      }
      
      return null;
   }
}

