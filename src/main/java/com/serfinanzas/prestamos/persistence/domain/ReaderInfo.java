package com.serfinanzas.prestamos.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reader_info")
@Getter
@Setter
@NoArgsConstructor
public class ReaderInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numberId;
    private String names;
    private String lastNames;
    private LocalDate birthDate;
    private String cellPhone;

    public ReaderInfo(int numberId, String names, String lastNames, LocalDate birthDate, String cellPhone) {
        this.numberId = numberId;
        this.names = names;
        this.lastNames = lastNames;
        this.birthDate = birthDate;
        this.cellPhone = cellPhone;
    }
}
