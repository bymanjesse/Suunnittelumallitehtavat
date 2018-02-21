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
import com.mycompany.varasto.dao.EmployeeDao;
import com.mycompany.varasto.entity.Employee;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;

public class EmployeeModel implements EmployeeDao {

    private static Session session;

    
    // hae työntekjät
    @Override
    public ObservableList<Employee> getEmployees() {
        
        // luodaan lista joka ottaa työntekijät vastaan
        ObservableList<Employee> list = FXCollections.observableArrayList();
        
        // haetaan hibernate sessio
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // haetaan työntekijät
        List<Employee> employees = session.createQuery("from Employee").list();
        session.beginTransaction().commit();
        // lisätään listaan
        employees.stream().forEach(list::add);

        // palauta lista
        return list;
    }

    @Override
    public Employee getEmployee(long id) {
        // haetaan hibernate sessio
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // haetaan työntekijä id:n perusteella
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();

        return employee;
    }
    
    @Override
    public String getEmployeeType(String username){
    
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // haetaan käyttäjänimern perusteella
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
       // ja palauteetaan admin / normaali käyttäjä
        return employee.getType();
    }

    @Override
    public void saveEmployee(Employee employee) {
           // tallennetaan työntekijä olio 
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }

    @Override
    public void updateEmployee(Employee employee) {
        
        
        // päivitetään työntekijä 
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setUserName(employee.getUserName());
        e.setPassword(employee.getPassword());
        e.setPhone(employee.getPhone());
        e.setAddress(employee.getAddress());
        session.getTransaction().commit();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        
        // poistetaan työntekijä id:n perusteella
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        session.delete(e);
        session.getTransaction().commit();
    }
    
    @Override
    public boolean checkUser(String username) {
        
        
        // tarkistetaan onko käyttäjää tietokanassa.
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();

        return employee != null;
    }
    
    @Override
    public boolean checkPassword(String username, String password) {
        
        // tarkistetaan salasana
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();

        return employee.getPassword().equals(password);
    }
}
