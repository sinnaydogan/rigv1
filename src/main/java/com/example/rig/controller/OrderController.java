package com.example.rig.controller;


import com.example.rig.dto.OrderDto;
import com.example.rig.exception.OutOfStockException;
import com.example.rig.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.save(orderDto));
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrderDto>> get() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<List<OrderDto>> getAllByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getAllByCustomerId(customerId));
    }

    @GetMapping("/getById/{orderId}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getById(orderId));
    }
}
