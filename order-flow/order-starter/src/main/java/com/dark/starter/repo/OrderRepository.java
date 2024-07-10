package com.dark.starter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dark.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
