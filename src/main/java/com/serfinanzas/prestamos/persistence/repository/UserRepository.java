package com.serfinanzas.prestamos.persistence.repository;

import com.serfinanzas.prestamos.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
