package com.serfinanzas.prestamos.persistence.repository;

import com.serfinanzas.prestamos.persistence.domain.ReaderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderInfoRepository extends JpaRepository<ReaderInfo, Integer> {

    ReaderInfo findByNumberId(int numberId);
}
