package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.StatisticsDto;
import com.getir.readingisgood.model.Statistic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
    StatisticsDto map(Statistic dto);
}
