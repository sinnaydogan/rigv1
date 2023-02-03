package com.example.rig.controller;

import com.example.rig.response.StatisticResponse;
import com.example.rig.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<StatisticResponse>> getStatisticByCustomer(@PathVariable Long customerId) {

        return ResponseEntity.ok(statisticService.getMonthlyOrderStatistic(customerId));
    }
}
