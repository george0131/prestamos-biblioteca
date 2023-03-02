package com.serfinanzas.prestamos.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lend_items")
@Getter
@Setter
@NoArgsConstructor
public class LendItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    Lend lend;
    @ManyToOne
    Book book;

    public LendItem(Lend lend, Book book) {
        this.lend = lend;
        this.book = book;
    }
}
