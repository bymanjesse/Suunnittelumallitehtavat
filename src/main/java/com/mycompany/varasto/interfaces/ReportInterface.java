/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.interfaces;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.entity.Lasku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ReportInterface {
     // A list that allows listeners to track changes when they occur.
    // guiden kautta tehty niin tästä en niin osaa sanoa. 
    public ObservableList<Lasku> REPORTLIST = FXCollections.observableArrayList();
}

