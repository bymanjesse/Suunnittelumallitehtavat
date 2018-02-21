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
import com.mycompany.varasto.entity.Lasku;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import com.mycompany.varasto.dao.LaskuDao;

public class LaskuModel implements LaskuDao {

    private static Session session;

    
    // haetaan kaikki 
    @Override
    public ObservableList<Lasku> getLaskut() {
        // luodaan lista johon ne lisätä
        ObservableList<Lasku> list = FXCollections.observableArrayList();
        // haetaan sessio
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // haetaan kaikki laskuista ja lisätään ne listaan
        List<Lasku> products = session.createQuery("from Lasku").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);
        // palautetaan lista
        return list;
    }

    @Override
    public Lasku getLasku(String id) {
        // sama kuin ylempi mutta vain yksi lasku joten ei listaa
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Lasku invoice = session.get(Lasku.class, id);
        session.getTransaction().commit();

        return invoice;
    }

    @Override
    public void saveLasku(Lasku lasku) {
        // tallennus
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(lasku);
        session.getTransaction().commit();
    }

    @Override
    public void deleteCategory(Lasku lasku) {
        // poisto
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Lasku i = session.get(Lasku.class, lasku.getId());
        session.delete(i);
        session.getTransaction().commit();
    }

}
