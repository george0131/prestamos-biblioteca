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

    @EmbeddedId
    private LendItemCompositeKey compositeKey;

    public LendItem(Lend lend, Book book) {
        this.compositeKey = new LendItemCompositeKey();
        this.compositeKey.lend = lend;
        this.compositeKey.book = book;
    }
}
