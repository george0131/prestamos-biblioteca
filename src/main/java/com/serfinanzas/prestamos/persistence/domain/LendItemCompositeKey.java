package com.serfinanzas.prestamos.persistence.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class LendItemCompositeKey implements Serializable {

    @OneToOne
    @JoinColumn(name = "lend_id", referencedColumnName = "id")
    Lend lend;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    Book book;
}
