package komposiitti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JESSE
 */
public class Main {


    public static void main(String[] args) {
        TietokoneTehdas tehdas = new TietokoneTehdas();
        Tietokone tietokone = new Tietokone(tehdas);
        
        System.out.println("Tietokoneen hinnaksi tulee: " + Double.toString(tietokone.getHinta()));
    }
    
}
