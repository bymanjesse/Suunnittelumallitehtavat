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
public class Strategia_1 implements ListConverter{

    @Override
    public String listToString(List<String> list) {
        String convertedString = "";
        for(String item : list) {
            convertedString += item + "\n";
        }
        return convertedString;
    }
    
}
