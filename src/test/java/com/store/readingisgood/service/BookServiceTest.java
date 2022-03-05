package com.store.readingisgood.service;

import com.store.readingisgood.dto.BookRequestDto;
import com.store.readingisgood.dto.BookUpdateRequestDto;
import com.store.readingisgood.exception.BookNotFoundException;
import com.store.readingisgood.mapper.BookMapper;
import com.store.readingisgood.model.Book;
import com.store.readingisgood.repository.BookRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Test
    void shouldPersist() {
        BookRequestDto dto = mock(BookRequestDto.class);

        Book book = new Book();
        when(bookMapper.map(dto)).thenReturn(book);
        when(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME)).thenReturn(3L);

        service.persist(dto);


        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());

        assertEquals(3L, captor.getValue().getId());
    }

    @Test
    @SneakyThrows
    void shouldUpdate() {
        Long bookId = 3L;
        Book book = new Book();
        BookUpdateRequestDto dto = new BookUpdateRequestDto();
        dto.setStockCount(99);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        service.update(bookId, dto);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);

        verify(bookRepository).save(captor.capture());
        assertEquals(99, captor.getValue().getStockCount());
    }

    @Test
    void shouldThrowNotFoundExWhenNotFound() {
        Long bookId = 3L;
        BookUpdateRequestDto dto = new BookUpdateRequestDto();
        dto.setStockCount(99);

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        try {
            service.update(bookId, dto);
        } catch (BookNotFoundException e) {
            bookId = 5L;
        } catch (Exception ex){
            fail();
        }

        assertEquals(5L, bookId);
    }

    @Test
    void shouldFindAll() {


    }
}