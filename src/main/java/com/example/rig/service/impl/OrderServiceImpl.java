package com.example.rig.service.impl;

import com.example.rig.dto.OrderDto;
import com.example.rig.entity.Book;
import com.example.rig.entity.Customer;
import com.example.rig.entity.Order;
import com.example.rig.exception.OutOfStockException;
import com.example.rig.repository.BookRepository;
import com.example.rig.repository.CustomerRepository;
import com.example.rig.repository.OrderRepository;
import com.example.rig.service.OrderService;
import org.hibernate.exception.DataException;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CustomerRepository customerRepository, BookRepository bookRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto save(OrderDto orderDto){
        Customer customer = customerRepository.getById(orderDto.getCustomerId());
        List<Book> bookList = new ArrayList<>();

        orderDto.getBookIds().forEach(id -> bookList.add(bookRepository.getById(id)));

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setCustomer(customer);
        order.setBookList(bookList);

         List<Long> outOfStockBooks = checkBookStock(bookList);
        if (outOfStockBooks.isEmpty()){
            orderRepository.save(order);
            updateBookStock(bookList);
            return null;
        } else {
            throw new OutOfStockException("Books with these ids are out of stock : " + outOfStockBooks);
        }
    }

    private List<Long> checkBookStock(List<Book> bookList) {
        List<Long> outOfStockBooks = new ArrayList<>();
        bookList.forEach(book -> {
            if (book.getStock() ==0){
                outOfStockBooks.add(book.getId());
            }
        });

        return outOfStockBooks;
    }

    private void updateBookStock(List<Book> bookList) {
        bookList.forEach(book -> {
            Long newBookStock = book.getStock();
            book.setStock(newBookStock -1);
            bookRepository.save(book);
        });
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OrderDto> getAll() {
        return null;
    }

    @Override
    public List<OrderDto> getAllByCustomerId(Long customerId) {
        Customer customer = customerRepository.getById(customerId);
        List<Order> orderList = orderRepository.findAllByCustomer(customer);
        List<OrderDto> orderDtoList = new ArrayList<>();

        orderList.forEach(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setCustomerId(order.getCustomer().getId());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setId(order.getId());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setBookIds(getOrderBookList(order));
            orderDtoList.add(orderDto);
        });
        Collections.sort(orderDtoList);
        return orderDtoList;
    }

    private List<Long> getOrderBookList(Order order) {
        List<Long> bookIds = new ArrayList<>();
        order.getBookList().forEach(book -> bookIds.add(book.getId()));
        return bookIds;
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto getById(Long orderId) {
        Order order = orderRepository.getById(orderId);

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setId(order.getId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setBookIds(getOrderBookList(order));

        return orderDto;
    }


}
