package com.getir.readingisgood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StatisticService {

    public void updateStatistics(int orderCount, int totalBookCount, BigDecimal totalPrice) {
        // TODO: 23/01/2022
    }
}
