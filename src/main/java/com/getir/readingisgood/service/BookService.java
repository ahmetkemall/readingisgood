package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.BookRequestDto;
import com.getir.readingisgood.dto.BookUpdateRequestDto;
import com.getir.readingisgood.dto.OrderItemRequestDto;
import com.getir.readingisgood.exception.BookNotFoundException;
import com.getir.readingisgood.mapper.BookMapper;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public void persist(BookRequestDto bookRequestDto) {
        Book newBook = bookMapper.map(bookRequestDto);
        newBook.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        bookRepository.save(newBook);
    }

    public void update(Long bookId, BookUpdateRequestDto bookRequestDto) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        book.setStockCount(bookRequestDto.getStockCount());
        update(book);
    }

    private void update(Book book) {
        try {
            bookRepository.save(book);
        } catch (OptimisticLockingFailureException e) {
            bookRepository.save(book);
        }
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByIds(List<Long> bookIdList) {
        return bookRepository.findByIdIn(bookIdList);
    }

    public void updateStock(List<OrderItemRequestDto> orderItems) {
    }
}
