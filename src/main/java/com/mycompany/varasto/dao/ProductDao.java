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
import com.mycompany.varasto.entity.Product;
import javafx.collections.ObservableList;

public interface ProductDao {
    
    // interface modelille
    
    public ObservableList<Product> getProducts();
    public Product getProduct(long id);
    public Product getProductByName(String productName);
    public void saveProduct(Product product);
    public void updateProduct(Product product);
    public void decreaseProduct(Product product);
    public void deleteProduct(Product product);
    public ObservableList<String> getProductNames();
    public void increaseProduct(Product product);
}
