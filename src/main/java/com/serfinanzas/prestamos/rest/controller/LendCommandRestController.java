package com.serfinanzas.prestamos.rest.controller;

import com.serfinanzas.prestamos.persistence.domain.Lend;
import com.serfinanzas.prestamos.rest.CommandRestController;
import com.serfinanzas.prestamos.rest.domain.LendInput;
import com.serfinanzas.prestamos.service.LendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api")
public class LendCommandRestController extends CommandRestController {

    private final LendService lendService;

    public LendCommandRestController(LendService lendService) {
        this.lendService = lendService;
    }

    @PostMapping(value = "/lend/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Lend> createLend(@RequestBody LendInput lendInput, Principal principal) {

        lendInput.setUsername(principal.getName());

        Lend created = lendService.createLend(lendInput);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
