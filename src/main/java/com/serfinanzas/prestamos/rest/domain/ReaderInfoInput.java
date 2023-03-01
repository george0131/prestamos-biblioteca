package com.serfinanzas.prestamos.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReaderInfoInput {

    private int documentId;
    private String names;
    private String lastNames;
    private LocalDate birthDate;
    private String cellPhone;

}
