package com.kaden.products.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.kaden.products.models.OrderModel;

@Entity
@Table(name = "ORDERS")
public class OrderEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ORDER_NUMBER")
    private String order_number;

    @Column(name = "PRODUCT_NAME")
    private String product_name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "QTY")
    private int quantity;

    public OrderEntity() {
    }

    public OrderEntity(int id, String order_number, String product_name, double price, int quantity) {
        this.id = id;
        this.order_number = order_number;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderModel toModel()
    {
        return new OrderModel(id, order_number, product_name, price, quantity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
