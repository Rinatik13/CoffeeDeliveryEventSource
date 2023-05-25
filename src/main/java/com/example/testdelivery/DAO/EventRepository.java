package com.example.testdelivery.DAO;

import com.example.testdelivery.entity.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<OrderEvent, Integer> {
}
