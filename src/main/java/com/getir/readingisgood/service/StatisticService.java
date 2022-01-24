package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.StatisticsDto;
import com.getir.readingisgood.mapper.StatisticMapper;
import com.getir.readingisgood.model.Statistic;
import com.getir.readingisgood.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;

    public void updateStatistics(int orderCount, int totalBookCount, BigDecimal totalPrice) {
        String date = getDateStr();

        Statistic statistic = statisticRepository.findById(date).orElse(null);

        if (statistic == null) {
            statistic = new Statistic(date, orderCount, totalBookCount, totalPrice);
        } else {
            statistic.addTotalAmount(totalPrice);
            statistic.addTotalBookCount(totalBookCount);
            statistic.addTotalOrderCount(orderCount);
        }

        statisticRepository.save(statistic);
    }

    private String getDateStr() {
        LocalDate today = LocalDate.now();
        int m = today.getMonth().getValue();
        int y = today.getYear();

        return m + "-" + y;
    }

    public List<StatisticsDto> findAll() {
        List<Statistic> statistics = statisticRepository.findAll();
        return statistics.stream().map(statisticMapper::map).collect(toList());
    }
}
