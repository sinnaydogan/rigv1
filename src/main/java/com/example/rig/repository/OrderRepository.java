package com.example.rig.repository;

import com.example.rig.entity.Customer;
import com.example.rig.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomer(Customer customer);


}
