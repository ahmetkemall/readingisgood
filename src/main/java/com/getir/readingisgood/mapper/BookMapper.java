package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.BookRequestDto;
import com.getir.readingisgood.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book map(BookRequestDto dto);
}
