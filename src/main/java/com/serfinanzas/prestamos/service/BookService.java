package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.domain.Book;
import com.serfinanzas.prestamos.rest.domain.BookInput;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    List<Book> search(String term);

    Book createBook(BookInput bookInput);
}
