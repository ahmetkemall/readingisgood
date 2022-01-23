package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookRequestDto;
import com.getir.readingisgood.dto.BookUpdateRequestDto;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.service.BookService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private BookService service;

    @Test
    void shouldPersistBook() {
        BookRequestDto dto = mock(BookRequestDto.class);
        controller.persistBook(dto);

        verify(service).persist(dto);
    }

    @Test
    @SneakyThrows
    void shouldUpdateBook() {
        BookUpdateRequestDto dto = mock(BookUpdateRequestDto.class);
        controller.updateBook(3L, dto);

        verify(service).update(3L, dto);

    }

    @Test
    void findAll() {
        List list = mock(List.class);
        when(service.findAll()).thenReturn(list);
        ResponseEntity<List<Book>> response = controller.findAll();

        assertEquals(list, response.getBody());
    }
}