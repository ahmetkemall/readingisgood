package com.getir.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Statistic")
public class Statistic {

    @Id
    private String date;

    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalAmount;

    public Statistic(String date, int totalOrderCount, int totalBookCount, BigDecimal totalAmount) {
        this.date = date;
        this.totalOrderCount = totalOrderCount;
        this.totalBookCount = totalBookCount;
        this.totalAmount = totalAmount;
    }

    @Version
    private Long version;

    public void addTotalAmount(BigDecimal totalPrice) {
        totalAmount = totalAmount.add(totalPrice);
    }

    public void addTotalBookCount(int totalBookCount) {
        setTotalBookCount(getTotalBookCount() + totalBookCount);
    }

    public void addTotalOrderCount(int orderCount) {
        setTotalOrderCount(getTotalOrderCount() + orderCount);
    }
}
