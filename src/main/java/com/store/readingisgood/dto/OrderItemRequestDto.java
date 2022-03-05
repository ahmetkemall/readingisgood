package com.store.readingisgood.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderItemRequestDto {

    @NotNull
    private Long bookId;

    @NotNull
    private Integer count;

}
