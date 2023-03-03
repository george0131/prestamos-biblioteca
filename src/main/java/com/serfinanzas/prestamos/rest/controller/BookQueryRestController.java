package com.serfinanzas.prestamos.rest.controller;

import com.serfinanzas.prestamos.persistence.domain.Book;
import com.serfinanzas.prestamos.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookQueryRestController {

    private final BookService bookService;

    public BookQueryRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Book>> getBooksByTerm(String term) {

        List<Book> founds = bookService.search(term);

        return new ResponseEntity<>(founds, HttpStatus.OK);

    }
}
