package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.domain.Lend;
import com.serfinanzas.prestamos.rest.domain.LendInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LendService {

    Lend createLend(LendInput input);
    Page<Lend> getAll(Pageable pageable);
}
