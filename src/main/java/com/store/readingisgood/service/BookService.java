package com.store.readingisgood.service;

import com.store.readingisgood.dto.BookRequestDto;
import com.store.readingisgood.dto.BookUpdateRequestDto;
import com.store.readingisgood.dto.OrderItemRequestDto;
import com.store.readingisgood.exception.BookNotFoundException;
import com.store.readingisgood.exception.NoStockException;
import com.store.readingisgood.mapper.BookMapper;
import com.store.readingisgood.model.Book;
import com.store.readingisgood.repository.BookRepository;
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

    public void updateStock(OrderItemRequestDto dto) throws NoStockException {
        Book book = bookRepository.findById(dto.getBookId()).orElse(null);
        if (book == null)
            return;
        if(book.getStockCount() - dto.getCount() < 1)
            throw new NoStockException(book.getId());

        book.takeStock(dto.getCount());
        bookRepository.save(book);
    }
}
