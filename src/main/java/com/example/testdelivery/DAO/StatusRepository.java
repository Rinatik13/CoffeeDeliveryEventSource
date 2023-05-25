package com.example.testdelivery.DAO;

import com.example.testdelivery.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
