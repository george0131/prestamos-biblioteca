package com.serfinanzas.prestamos.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "lend")
@Getter
@Setter
@NoArgsConstructor
public class Lend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "reader_info_id", referencedColumnName = "id")
    private ReaderInfo readerInfo;
    private LocalDate createdOn;
    private LocalDate returnOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "book_id")
    private LendItem item;

    public Lend(User user, ReaderInfo readerInfo, LocalDate createdOn, LocalDate returnOn) {
        this.user = user;
        this.readerInfo = readerInfo;
        this.createdOn = createdOn;
        this.returnOn = returnOn;
    }
}
