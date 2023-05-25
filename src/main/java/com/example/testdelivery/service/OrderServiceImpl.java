package com.example.testdelivery.service;

import com.example.testdelivery.DAO.*;
import com.example.testdelivery.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Value("${status.Registered}")
    private int registration;

    @Value("${status.Cancellation}")
    private int cancellation;

    @Value("${status.Issued}")
    private int issued;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    @Transactional
    public void publishEvent(OrderEvent event) {
        if (checkEvent(event)) {
            eventRepository.save(event);
        }
    }

    private boolean checkEvent(OrderEvent event) {
        List<OrderEvent> events = generatorActualEventList(event.getOrder_id());
        if(events.isEmpty() && event.getStatus()==registration){
            return true;
        }
        else if(checkBlockingStatus(events) && event.getStatus()!=registration){
            return true;
        }else {
            return false;
        }
    }

    private List<OrderEvent> generatorActualEventList(int id) {
        List<OrderEvent> actualEvents = new ArrayList<>();
        enumerationEndAddEvent(actualEvents,id);
        return actualEvents;
    }

    private void enumerationEndAddEvent(List<OrderEvent> actualEvents, int id) {
        for(OrderEvent event: eventRepository.findAll()){
            if(event.getOrder_id()==id){
                actualEvents.add(event);
            }
        }
    }

    private boolean checkBlockingStatus(List<OrderEvent> events) {
        boolean flagAddEvent = true;
        boolean flagRegistration = false;
        for(OrderEvent event: events){
            if((event.getStatus() == cancellation) || (event.getStatus() == issued)){
                flagAddEvent = false;
            }
            if(event.getStatus() == registration){
                flagRegistration = true;
            }
        }
        return flagAddEvent && flagRegistration;
    }

    @Override
    @Transactional
    public Order findOrder(int id) {
        return orderBuilder(generatorActualEventList(id));
    }

    private Order orderBuilder(List<OrderEvent> events) {
        Order order = new Order();
        for(OrderEvent event:events){
            layoutOrder(order,event);
        }
        return order;
    }

    private void layoutOrder(Order order, OrderEvent event) {
        addOrderId(order,event);
        buildStatus(order,event);
        buildConsumer(order,event);
        buildEmployee(order,event);
        buildProduct(order,event);
        buildPlanDateDelivery(order,event);
    }

    private void buildPlanDateDelivery(Order order, OrderEvent event) {
        if(order.getPlan_date_delivery()==null){
            addPlan_date_delivery(event,order);
        } else if(!order.getPlan_date_delivery().equals((event.getPlan_date_delivery())) && event.getPlan_date_delivery() != null){
            addPlan_date_delivery(event,order);
        }
        if(order.getDate()==null){
            order.setDate(event.getDate());
        }else if(!order.getDate().equals(event.getDate()) && event.getDate()==null){
            order.setDate(event.getDate());
        }
    }

    private void buildProduct(Order order, OrderEvent event) {
        if(order.getProduct()==null){
            addProduct(event,order);
        }
        else if(order.getProduct().getId()!=event.getProduct_id() && event.getProduct_id() != 0){
            if (!order.getProduct().getPrice().equals(event.getPrice()) && event.getPrice() != null){
                addProduct(event,order);
            }
        }
    }

    private void buildEmployee(Order order, OrderEvent event) {
        if (order.getEmployee()==null){
            addEmployee(event,order);
        }
        // необходимо проверить был ли изменён сотрудник
        else if(order.getEmployee().getId() != event.getEmployee_id() && event.getEmployee_id() != 0){
            addEmployee(event,order);
        }
    }

    private void buildConsumer(Order order, OrderEvent event) {
        if (order.getConsumer()==null){
            addConsumer(event,order);
        }
        // необходимо проверить был ли изменён покупатель
        else if(order.getConsumer().getId() != event.getConsumer_id() && event.getConsumer_id() != 0){
            addConsumer(event,order);
        }
    }

    private void buildStatus(Order order, OrderEvent event) {
        if(order.getStatus()==null)
        {
            addStatus(event,order);
        }
        else if(order.getStatus().getId()!=event.getStatus()){
            addStatus(event,order);
        }
    }

    private void addOrderId(Order order, OrderEvent event) {
        if(order.getId()==0){
            order.setId(event.getOrder_id());
        }
    }

    private void addPlan_date_delivery(OrderEvent event, Order order) {
        order.setPlan_date_delivery(event.getPlan_date_delivery());
    }

    private void addProduct(OrderEvent event, Order order) {
        Product product = new Product();
        product.setId(event.getProduct_id());
        product.setPrice(event.getPrice());
        order.setProduct_id(product);
    }

    private void addEmployee(OrderEvent event, Order order) {
        Employee employee = new Employee();
        employee.setId(event.getEmployee_id());
        order.setEmployee_id(employee);
    }

    private void addConsumer(OrderEvent event, Order order) {
        Consumer consumer = new Consumer();
        consumer.setId(event.getConsumer_id());
        order.setConsumer_id(consumer);
    }

    private void addStatus(OrderEvent event, Order order) {
        if(order.getPast_status()==null){
            order.setPast_status(new ArrayList<>());
        }
        if(order.getStatus()==null){
            order.setStatus_id(statusRepository.findById(event.getStatus()).get());
        }else{
            PastStatus pastStatus = new PastStatus();
            pastStatus.setId(order.getStatus().getId());
            pastStatus.setName(order.getStatus().getName());
            pastStatus.setDate(order.getDate());
            order.getPast_status().add(pastStatus);
            order.setStatus_id(statusRepository.findById(event.getStatus()).get());
        }
        if(event.getStatus()==cancellation){
            order.setCancellation_reasons(event.getCancellation_reasons());
        }
    }
}
