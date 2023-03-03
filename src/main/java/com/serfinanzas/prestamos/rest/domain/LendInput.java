package com.serfinanzas.prestamos.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LendInput {

    private String username;
    private ReaderInfoInput readerInfo;
    private LendItemInput lendItem;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnOn;
}
