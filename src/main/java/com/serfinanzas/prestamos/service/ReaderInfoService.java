package com.serfinanzas.prestamos.service;

import com.serfinanzas.prestamos.persistence.domain.ReaderInfo;

public interface ReaderInfoService {

    ReaderInfo findByDocumentId(int documentId);
}
