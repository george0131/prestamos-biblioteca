package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.domain.Lend;
import com.serfinanzas.prestamos.rest.domain.LendInput;

public interface LendService {

    Lend createLend(LendInput input);
}
