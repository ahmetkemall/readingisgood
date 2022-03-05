package com.store.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Book")
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "SEQ_BOOK";

    @Id
    private Long id;

    private String name;

    private int stockCount;

    private BigDecimal price;

    @Version
    private Long version;

    public void takeStock(Integer count) {
        setStockCount(stockCount - count);
    }
}
