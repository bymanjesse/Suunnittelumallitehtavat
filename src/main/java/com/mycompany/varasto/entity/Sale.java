/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.varasto.entity;

/**
 *
 * @author aleks
 */
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


// myynti luokka hibernatella ja perus getteri ja setterit jne jne
@Entity
@Table(name = "sales")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "laskuId")
    private Lasku lasku;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "quantity")
    private double quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "total")
    private double total;
    @Column(name = "datetime", insertable=false)
    private String date;

    public Sale() {
    }

    public Sale(long id, Lasku lasku, Product product, double quantity, double price, double total, String date) {
        this.id = id;
        this.lasku = lasku;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.date = date;
    }

    public Sale(Lasku lasku, Product product, double quantity, double price, double total) {
        this.lasku = lasku;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lasku getlasku() {
        return lasku;
    }

    public void setLasku(Lasku lasku) {
        this.lasku = lasku;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + 
                ", lasku=" + lasku + 
                ", product=" + product + 
                ", quantity=" + quantity + 
                ", price=" + price + 
                ", total=" + total + 
                ", date=" + date + '}';
    }
    
}
