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
import com.mycompany.varasto.entity.Purchase;
import javafx.collections.ObservableList;


public interface PurchaseDao {
    
    // interface modelille
    
    public ObservableList<Purchase> getPurchases();
    public Purchase getPurchase(long id);
    public void savePurchase(Purchase purchase);
    public void updatePurchase(Purchase purchase);
    public void deletePurchase(Purchase purchase);
}

