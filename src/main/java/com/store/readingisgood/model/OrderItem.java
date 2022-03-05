package com.store.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "OrderItem")
public class OrderItem {

    @Transient
    public static final String SEQUENCE_NAME = "SEQ_ORDER_ITEM";

    @Id
    private Long id;

    private Long orderId;
    private long bookId;
    private int count;
    private BigDecimal totalAmount;

}
