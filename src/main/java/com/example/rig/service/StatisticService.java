package com.example.rig.service;

import com.example.rig.response.StatisticResponse;

import java.util.List;

public interface StatisticService {

    List<StatisticResponse> getMonthlyOrderStatistic(Long customerId);
}
