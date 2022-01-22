package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.StatisticsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics(){
        StatisticsDto dto = new StatisticsDto();
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }



}
