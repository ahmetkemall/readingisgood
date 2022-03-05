package com.store.readingisgood.mapper;

import com.store.readingisgood.dto.StatisticsDto;
import com.store.readingisgood.model.Statistic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
    StatisticsDto map(Statistic dto);
}
