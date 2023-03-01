package com.serfinanzas.prestamos.persistence.repository;

import com.serfinanzas.prestamos.persistence.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
