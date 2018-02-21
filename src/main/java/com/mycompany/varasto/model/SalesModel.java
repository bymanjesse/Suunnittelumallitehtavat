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
import com.mycompany.varasto.dao.SaleDao;
import com.mycompany.varasto.entity.Sale;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class SalesModel implements SaleDao {

    private static Session session;

    
    // haetaan kaikki myyntitapahtumat ja laitetaan ne listaan
    @Override
    public ObservableList<Sale> getSales() {

        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Sale> products = session.createQuery("from Sale").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);

        return list;
    }
    // haetaan tietyn tuotteen id:n perusteella kaikki sen tyotteen myynnit
    @Override
    public ObservableList<Sale> getSaleByProductId(long id) {

        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        List<Sale> products = (List<Sale>) session.createCriteria(Sale.class)
                .add(Restrictions.eq("product.id", id)).list();

        session.beginTransaction().commit();
        products.stream().forEach(list::add);
        return list;
    }

    
    // haetaan myyntID perusteella myynti
    @Override
    public Sale getSale(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale sale = session.get(Sale.class, id);
        session.getTransaction().commit();

        return sale;
    }
    // tallennetaan myynti
    @Override
    public void saveSale(Sale sale) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(sale);
        session.getTransaction().commit();
    }
    // päivitetään myynti
    @Override
    public void updateSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        s.setProduct(sale.getProduct());
        s.setQuantity(sale.getQuantity());
        s.setPrice(sale.getPrice());
        s.setTotal(sale.getTotal());
        s.setDate(sale.getDate());
        session.getTransaction().commit();
    }
    // poisto
    @Override
    public void deleteSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        session.delete(s);
        session.getTransaction().commit();
    }

}
