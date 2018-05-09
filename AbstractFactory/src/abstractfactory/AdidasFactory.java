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
public class AdidasFactory extends AbstractFactory {
   
    Jasper getVaate(String vaate) {
   
      if(vaate == null){
         return null;
      }		
      
      if(vaate.equalsIgnoreCase("HOUSUT")){
         return new AdidasHousut();
         
      }else if(vaate.equalsIgnoreCase("PAITA")){
         return new AdidasPaita();
         
      }else if(vaate.equalsIgnoreCase("LIPPIS")){
         return new AdidasLippis();
                 
      }else if(vaate.equalsIgnoreCase("KENGÄT")){
         return new AdidasKengät();
      }
      
      return null;
   }
}
