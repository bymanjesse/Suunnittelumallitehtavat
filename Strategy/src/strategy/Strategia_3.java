/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.List;
/**
 *
 * @author JESSE
 */
public class Strategia_3 implements ListConverter{

    @Override
    public String listToString(List<String> list) {
        String convertedString = "";
        for(int i = 0; i < list.size(); i++) {
            if(i % 3 == 0) {
                convertedString += "\n";
            }
            else {
                convertedString += " ";
            } 
            convertedString += list.get(i);
        }
        return convertedString;
    }
    
}
