/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.model;

/**
 *
 * @author aleks
 */
import com.mycompany.varasto.HibernateUtil;
import com.mycompany.varasto.dao.ProductDao;
import com.mycompany.varasto.entity.Product;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class ProductModel implements ProductDao {

    private static Session session;

    @Override
    public ObservableList<Product> getProducts() {
        // haetaan kaikki tuotteet 
        // luodaan lista tuotteelle
        ObservableList<Product> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // haetaan kaikki tuotteet ja lisätään ne listaan
        List<Product> products = session.createQuery("from Product").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);
        // ja palautetaan lista
        return list;
    }

    @Override
    public Product getProduct(long id) {
        // haetaan yksi tuote 
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();

        return product;
    }

    @Override
    public Product getProductByName(String productName) {
        // haetaan tuote nimellä
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product where productName=:name");
        query.setParameter("name", productName);
        Product product = (Product) query.uniqueResult();
        
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        // tallenetaan tuote
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    @Override
    public void updateProduct(Product product) {
        // päivitetään tuote
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setProductName(product.getProductName());
        p.setCategory(product.getCategory());
        p.setQuantity(product.getQuantity());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        session.getTransaction().commit();
    }
    
    @Override
    public void increaseProduct(Product product){
        // lisätään tuotteen määrää
        // käytetään ostossa
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setQuantity(product.getQuantity());
        session.getTransaction().commit();
    }
    
    @Override
    public void decreaseProduct(Product product){
        // vähennetään tuotteen määrää
        // käytetään myynnissä
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setQuantity(product.getQuantity());
        session.getTransaction().commit();
    }

    @Override
    // poistetaan tuote
    public void deleteProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        session.delete(p);
        session.getTransaction().commit();
    }
    
    @Override
    
    // haetaan tuotteiden nimet listaan
    public ObservableList<String> getProductNames(){
    
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.setProjection(Projections.property("productName"));
        ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
        session.getTransaction().commit();
        
        return list;
    }
}
