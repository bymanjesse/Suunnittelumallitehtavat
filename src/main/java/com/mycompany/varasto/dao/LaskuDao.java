/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.dao;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Lasku;
import javafx.collections.ObservableList;

// interface Modelille
public interface LaskuDao {
 
    public ObservableList<Lasku> getLaskut();
    public Lasku getLasku(String id);
    public void saveLasku(Lasku lasku);
    public void deleteCategory(Lasku lasku);
}
