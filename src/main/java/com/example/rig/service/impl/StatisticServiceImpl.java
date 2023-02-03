package com.example.rig.service.impl;

import com.example.rig.entity.Customer;
import com.example.rig.entity.Order;
import com.example.rig.repository.CustomerRepository;
import com.example.rig.repository.OrderRepository;
import com.example.rig.response.StatisticResponse;
import com.example.rig.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public StatisticServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<StatisticResponse> getMonthlyOrderStatistic(Long customerId) {
        Customer customer = customerRepository.getById(customerId);
        List<Order> orderList = orderRepository.findAllByCustomer(customer);

        List<StatisticResponse> statisticResponseList = new ArrayList<>();


        orderList.forEach(order -> {
            StatisticResponse statisticResponse = new StatisticResponse();
            int month = order.getOrderDate().getMonth();
            switch (month) {
                case 0: statisticResponse.setMonth("Jan"); break;
                case 1: statisticResponse.setMonth("Feb"); break;
                case 2: statisticResponse.setMonth("Mar"); break;
                case 3: statisticResponse.setMonth("Apr"); break;
                case 4: statisticResponse.setMonth("May"); break;
                case 5: statisticResponse.setMonth("Jun"); break;
                case 6: statisticResponse.setMonth("Jul"); break;
                case 7: statisticResponse.setMonth("Aug"); break;
                case 8: statisticResponse.setMonth("Sep"); break;
                case 9: statisticResponse.setMonth("Oct"); break;
                case 10: statisticResponse.setMonth("Nov"); break;
                case 11: statisticResponse.setMonth("Dec"); break;
            }
            statisticResponse.setTotalOrderCount(1L);
            statisticResponse.setTotalBookCount(order.getBookList().size());
            statisticResponse.setTotalPurchasedAmount(order.getTotalPrice());
            statisticResponseList.add(statisticResponse);
        });

        Map<String, StatisticResponse> map = new HashMap<>();

        statisticResponseList.forEach(statisticResponse1 -> {
            String month = statisticResponse1.getMonth();
            if (map.containsKey(month)){
                StatisticResponse other = map.get(month);
                other.setTotalOrderCount(map.get(month).getTotalOrderCount() + statisticResponse1.getTotalOrderCount());
                other.setTotalBookCount(map.get(month).getTotalBookCount() + statisticResponse1.getTotalBookCount());
                other.setTotalPurchasedAmount(statisticResponse1.getTotalPurchasedAmount());
                other.setTotalPurchasedAmount(other.getTotalPurchasedAmount().add(map.get(month).getTotalPurchasedAmount()));
            }else {
                map.put(month, statisticResponse1);
            }
        });

//        statisticResponseList.forEach(
//                statisticResponse1 -> {
//                    switch (statisticResponse1.getMonth()) {
//                        case "Jan": {
//                            statisticResponse1.setMonth("Jan");
//                            statisticResponse1.setTotalBookCount(statisticResponse1.getTotalBookCount() + 1);
//                            break;
//                        }
//
//                        case "Feb": statisticResponse.setMonth("Feb"); break;
//                        case "Mar": statisticResponse.setMonth("Mar"); break;
//                        case "Apr": statisticResponse.setMonth("Apr"); break;
//                        case "May": statisticResponse.setMonth("May"); break;
//                        case "Jun": statisticResponse.setMonth("Jun"); break;
//                        case "Jul": statisticResponse.setMonth("Jul"); break;
//                        case "Aug": statisticResponse.setMonth("Aug"); break;
//                        case "Sep": statisticResponse.setMonth("Sep"); break;
//                        case "Oct": statisticResponse.setMonth("Oct"); break;
//                        case "Nov": statisticResponse.setMonth("Nov"); break;
//                        case "Dec": statisticResponse.setMonth("Dec"); break;
//                    }
//                }
//        );

        List<StatisticResponse> merged = new ArrayList<>(map.values());
        return merged;
    }

    private int getMothInfo(Date orderDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderDate);
        int month = cal.get(Calendar.MONTH);
        return month;
    }
}
