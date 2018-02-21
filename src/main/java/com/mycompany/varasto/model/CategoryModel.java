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
import com.mycompany.varasto.dao.CategoryDao;
import com.mycompany.varasto.entity.Category;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

public class CategoryModel implements CategoryDao {

    private static Session session;

    
    // hae categoriat 
    @Override
    public ObservableList<Category> getCategories() {

        ObservableList<Category> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Category> categories = session.createQuery("from Category").list();
        session.beginTransaction().commit();
        categories.stream().forEach(list::add);

        return list;
    }

    
    // hae categoriat
    @Override
    public Category getCategory(long id) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category category = session.get(Category.class, id);
        session.getTransaction().commit();

        return category;
    }
    
    
    // tallenna categoria
    @Override
    public void saveCategory(Category category) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
    }

    // päivitä categoria
    @Override
    public void updateCategory(Category category) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category c = session.get(Category.class, category.getId());
        c.setType(category.getType());
        c.setDescription(category.getDescription());
        session.getTransaction().commit();
    }

    // poista categoria
    @Override
    public void deleteCategory(Category category) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category c = session.get(Category.class, category.getId());
        session.delete(c);
        session.getTransaction().commit();
    }

    
    // hae categoria tyypit
    @Override
    public ObservableList<String> getTypes() {
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        criteria.setProjection(Projections.property("type"));
        ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
        session.getTransaction().commit();
        
        return list;
    }

}
