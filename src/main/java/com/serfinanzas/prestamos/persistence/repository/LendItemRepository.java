package com.serfinanzas.prestamos.persistence.repository;

import com.serfinanzas.prestamos.persistence.domain.LendItem;
import com.serfinanzas.prestamos.persistence.domain.LendItemCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendItemRepository extends JpaRepository<LendItem, LendItemCompositeKey> {
}
