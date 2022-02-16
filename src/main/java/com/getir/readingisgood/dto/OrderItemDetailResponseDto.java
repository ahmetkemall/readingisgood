package com.getir.readingisgood.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetailResponseDto {

    private Long bookId;

    private Integer count;

}
