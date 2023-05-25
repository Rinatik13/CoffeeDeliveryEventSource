package com.example.testdelivery.controller;

import com.example.testdelivery.entity.OrderEvent;
import com.example.testdelivery.entity.Order;
import com.example.testdelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    OrderService service;

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody OrderEvent event){
       service.publishEvent(event);
    }

    @GetMapping("/get/{id}")
    public Order getEvent(@PathVariable int id){
        return service.findOrder(id);
    }
}
