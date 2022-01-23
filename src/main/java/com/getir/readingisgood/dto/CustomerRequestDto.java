package com.getir.readingisgood.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank
    @Size(max = 300)
    private String name;

    @NotBlank
    @Size(max = 300)
    private String email;

}
