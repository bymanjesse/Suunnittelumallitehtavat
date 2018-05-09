/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractfactory;

import java.util.Scanner;

/**
 *
 * @author JESSE
 */
public class AbstractFactoryPatternDemo {
   public static void main(String[] args) {
      String i = "O";
      System.out.println("Jasperin ollessa opiskelija, kirjoita Adidas "
              + "hänen ollessa valmistunut, kirjoita Boss.");
      System.out.println("Lopettaaksesi ohjelman, kirjoita Q");
      System.out.println("Onko Jasper opiskelija vai valmistunut?");
      while(i!="Q"){

      Scanner sc = new Scanner(System.in);
      i = sc.nextLine();

      switch (i) {
      case"Adidas":
      AbstractFactory Factory = FactoryProducer.getFactory("ADIDAS");
      Jasper vaate = Factory.getVaate("LIPPIS");
      vaate.pue();
      Jasper vaate2 = Factory.getVaate("PAITA");
      vaate2.pue();
      Jasper vaate3 = Factory.getVaate("HOUSUT");
      vaate3.pue();     
      Jasper vaate4 = Factory.getVaate("KENGÄT");   
      vaate4.pue();
      break;
      case"Boss":
      AbstractFactory Factory2 = FactoryProducer.getFactory("BOSS");
      Jasper vaate5 = Factory2.getVaate("LIPPIS");
      vaate5.pue();
      Jasper vaate6 = Factory2.getVaate("PAITA");
      vaate6.pue();
      Jasper vaate7 = Factory2.getVaate("HOUSUT");
      vaate7.pue();
      Jasper vaate8 = Factory2.getVaate("HOUSUT");
      vaate8.pue();
      break;
      default:
          System.out.println("Jasper ei pue mitään päälleen");
      }
      }
   }
   }

