package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.OrderPlaceResponseDto;
import com.getir.readingisgood.dto.OrderRequestDto;
import com.getir.readingisgood.dto.StatisticsDto;
import com.getir.readingisgood.exception.CustomerNotFoundException;
import com.getir.readingisgood.exception.NoStockException;
import com.getir.readingisgood.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<List<StatisticsDto>> getStatistics() {
        List<StatisticsDto> dtoList = statisticService.findAll();
        return new ResponseEntity<>(dtoList, HttpStatus.ACCEPTED);
    }


}
