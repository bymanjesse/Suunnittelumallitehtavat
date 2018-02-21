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
import com.mycompany.varasto.dao.SupplierDao;
import com.mycompany.varasto.entity.Supplier;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class SupplierModel implements SupplierDao {

    private static Session session;

    
    // haetaan kaikki supplierit ja lisätään ne listaan ja palautetaan lista
    @Override
    public ObservableList<Supplier> getSuppliers() {

        ObservableList<Supplier> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Supplier> suppliers = session.createQuery("from Supplier").list();
        session.beginTransaction().commit();
        suppliers.stream().forEach(list::add);

        return list;
    }

    
    // haetaan supplier ID:n perusteella
    @Override
    public Supplier getSupplier(long id) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Supplier supplier = session.get(Supplier.class, id);
        session.getTransaction().commit();

        return supplier;
    }

    
    // tallennetaan supplier
    @Override
    public void saveSuplier(Supplier supplier) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(supplier);
        session.getTransaction().commit();
    }
    
    // päivitetän supplier
    @Override
    public void updateSuplier(Supplier supplier) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Supplier s = session.get(Supplier.class, supplier.getId());
        s.setName(supplier.getName());
        s.setPhone(supplier.getPhone());
        s.setAddress(supplier.getAddress());
        session.getTransaction().commit();
    }

    
    // poistetaan supplier
    @Override
    public void deleteSuplier(Supplier supplier) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Supplier s = session.get(Supplier.class, supplier.getId());
        session.delete(s);
        session.getTransaction().commit();
    }
    // haetaan nimet
    @Override
    public ObservableList<String> getNames(){
    
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Supplier.class);
        criteria.setProjection(Projections.property("name"));
        ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
        session.getTransaction().commit();
        
        return list;
    }

}
