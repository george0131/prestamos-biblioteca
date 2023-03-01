package com.serfinanzas.prestamos.persistence.repository;

import com.serfinanzas.prestamos.persistence.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByIdLib(int code);
}
