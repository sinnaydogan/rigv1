package com.example.rig.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDto implements Comparable<OrderDto> {
    private Long id;
    private BigDecimal totalPrice;
    private List<Long> bookIds;
    private Long customerId;

    private Date orderDate;

    @Override
    public int compareTo(OrderDto orderDto) {
        return getOrderDate().compareTo(orderDto.getOrderDate());

    }
}
