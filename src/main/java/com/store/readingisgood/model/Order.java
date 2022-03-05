package com.store.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Order")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "SEQ_ORDER";

    @Id
    private Long id;

    private long customerId;

    private Date startDate;

    private Date endDate;

    private long orderLongUx;

    public long getUnixDateDiff() {
        if(startDate == null || endDate == null)
            return 0;

        return endDate.getTime() - startDate.getTime();
    }
}
