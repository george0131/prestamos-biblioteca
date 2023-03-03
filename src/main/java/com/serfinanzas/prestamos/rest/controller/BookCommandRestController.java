package com.serfinanzas.prestamos.rest.controller;

import com.serfinanzas.prestamos.persistence.domain.Book;
import com.serfinanzas.prestamos.rest.CommandRestController;
import com.serfinanzas.prestamos.rest.domain.BookInput;
import com.serfinanzas.prestamos.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookCommandRestController extends CommandRestController {

    private final BookService service;

    public BookCommandRestController(BookService service) {
        this.service = service;
    }

    @PostMapping("/book/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Book> createBook(BookInput input) {

        return new ResponseEntity<>(service.createBook(input), HttpStatus.CREATED);
    }
}
