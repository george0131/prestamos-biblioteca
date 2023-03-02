package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.domain.ReaderInfo;
import com.serfinanzas.prestamos.persistence.repository.ReaderInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReaderInfoServiceImpl implements ReaderInfoService {

    private final ReaderInfoRepository repository;

    public ReaderInfoServiceImpl(ReaderInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ReaderInfo findByDocumentId(int documentId) {

        return repository.findByNumberId(documentId);
    }
}
