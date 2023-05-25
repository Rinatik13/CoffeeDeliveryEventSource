package com.example.testdelivery.entity;

import java.util.List;

public class Order {

    private int id;

    private Status status_id;

    private Consumer consumer_id;

    private Employee employee_id;

    private Product product_id;

    private String plan_date_delivery;

    private List<PastStatus> past_status;

    private String cancellation_reasons;

    private String date;

    public Order() {
    }

    public String getCancellation_reasons() {
        return cancellation_reasons;
    }

    public void setCancellation_reasons(String cancellation_reasons) {
        this.cancellation_reasons = cancellation_reasons;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<PastStatus> getPast_status() {
        return past_status;
    }

    public void setPast_status(List<PastStatus> past_status) {
        this.past_status = past_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status_id;
    }

    public void setStatus_id(Status status) {
        this.status_id = status;
    }

    public Consumer getConsumer() {
        return consumer_id;
    }

    public void setConsumer_id(Consumer consumer) {
        this.consumer_id = consumer;
    }

    public Product getProduct() {
        return product_id;
    }

    public void setProduct_id(Product product) {
        this.product_id = product;
    }

    public Employee getEmployee() {
        return employee_id;
    }

    public void setEmployee_id(Employee employee) {
        this.employee_id = employee;
    }

    public String getPlan_date_delivery() {
        return plan_date_delivery;
    }

    public void setPlan_date_delivery(String plan_date_delivery) {
        this.plan_date_delivery = plan_date_delivery;
    }
}
