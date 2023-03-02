package com.serfinanzas.prestamos.persistence.repository;

import com.serfinanzas.prestamos.persistence.domain.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LendRepository extends JpaRepository<Lend, Integer> {

    @Query("SELECT l FROM Lend l \n" +
            "JOIN LendItem li ON l.id = li.book.id \n" +
            "WHERE li.book.id = :bookId AND l.returnOn > current_date")
    public Lend checkIfBookIsInLoan(@Param("bookId") int bookId);
}
