package com.getir.readingisgood.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

}
