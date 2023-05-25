package com.example.testdelivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int consumer_id;
    private int order_id;
    private int employee_id;
    private String plan_date_delivery;
    private int product_id;
    private BigDecimal price;
    private String date;
    private String cancellation_reasons;
    private int status;

    public OrderEvent() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(int consumer_id) {
        this.consumer_id = consumer_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getPlan_date_delivery() {
        return plan_date_delivery;
    }

    public void setPlan_date_delivery(String plan_date_delivery) {
        this.plan_date_delivery = plan_date_delivery;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCancellation_reasons() {
        return cancellation_reasons;
    }

    public void setCancellation_reasons(String cancellation_reasons) {
        this.cancellation_reasons = cancellation_reasons;
    }
}
