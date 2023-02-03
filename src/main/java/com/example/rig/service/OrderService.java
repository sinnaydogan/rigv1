package com.example.rig.service;

import com.example.rig.dto.OrderDto;
import com.example.rig.exception.OutOfStockException;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderService {
    OrderDto save(OrderDto orderDto) throws OutOfStockException;

    void delete(Long id);

    List<OrderDto> getAll();
    List<OrderDto> getAllByCustomerId(Long customerId);

    Page<OrderDto> getAll(Pageable pageable);

    OrderDto getById(Long orderId);
}
