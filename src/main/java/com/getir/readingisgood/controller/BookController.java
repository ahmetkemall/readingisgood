package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookRequestDto;
import com.getir.readingisgood.dto.BookUpdateRequestDto;
import com.getir.readingisgood.exception.BookNotFoundException;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public void persistBook(@RequestBody @Valid BookRequestDto bookRequestDto) {
        bookService.persist(bookRequestDto);
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable Long bookId, @RequestBody @Valid BookUpdateRequestDto bookRequestDto)
            throws BookNotFoundException {
        bookService.update(bookId, bookRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.ACCEPTED);
    }
}
