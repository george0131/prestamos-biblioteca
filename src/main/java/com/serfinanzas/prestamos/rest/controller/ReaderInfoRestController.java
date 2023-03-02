package com.serfinanzas.prestamos.rest.controller;

import com.serfinanzas.prestamos.persistence.domain.ReaderInfo;
import com.serfinanzas.prestamos.service.ReaderInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReaderInfoRestController {

    private final ReaderInfoService service;

    public ReaderInfoRestController(ReaderInfoService service) {
        this.service = service;
    }

    @GetMapping("/reader-info/{docId}")
    public ResponseEntity<ReaderInfo> getReaderInfo(@PathVariable("docId") int docId) {

        ReaderInfo found = service.findByDocumentId(docId);

        if (found != null)
            return new ResponseEntity<>(found, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
