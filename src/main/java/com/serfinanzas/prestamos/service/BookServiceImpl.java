package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.domain.Book;
import com.serfinanzas.prestamos.persistence.repository.BookRepository;
import com.serfinanzas.prestamos.rest.domain.BookInput;
import com.serfinanzas.prestamos.service.exception.LendException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Book> search(String term) {

        return repository.getAllByNameContainingIgnoreCase(term);
    }

    @Override
    public Book createBook(BookInput bookInput) {

        if (repository.findByIdLib(bookInput.getCode()) != null)
            throw new LendException(String.format("El libro con código %s ya existe.", bookInput.getCode()), 500);

        Book book = new Book();
        book.setIdLib(bookInput.getCode());
        book.setName(bookInput.getName());

        try {
            return repository.saveAndFlush(book);
        } catch (Exception e) {
            throw new LendException(String.format("Ocurrió un error creando el libro %s", bookInput.getName()), 500);
        }
    }
}
