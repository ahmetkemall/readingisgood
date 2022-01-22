package com.getir.readingisgood.dto;

import javax.validation.constraints.Size;

public class BookRequestDto {

    private long id;

    @Size(max = 200)
    private String name;

    private int count;

}
