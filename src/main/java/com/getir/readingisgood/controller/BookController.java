package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @PostMapping
    public ResponseEntity<Void> persistBook(BookRequestDto bookRequestDto) {
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateBook(BookRequestDto bookRequestDto) {
        return ResponseEntity.noContent().build();
    }
}
