package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

    @NotBlank
    @Size(max = 200)
    private String name;

    @Min(1)
    @Max(9999)
    private int stockCount;

    @DecimalMin(value = "1")
    private BigDecimal price;
}
