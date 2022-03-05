package com.store.readingisgood.mapper;

import com.store.readingisgood.dto.BookRequestDto;
import com.store.readingisgood.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book map(BookRequestDto dto);
}
