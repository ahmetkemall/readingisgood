package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends MongoRepository<Statistic, Long> {

}
