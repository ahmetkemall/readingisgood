package com.store.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class BookUpdateRequestDto {

    @Min(0)
    @Max(9999)
    private int stockCount;

}
