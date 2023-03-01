package com.serfinanzas.prestamos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.serfinanzas.prestamos.persistence.repository"})
@ComponentScan(basePackages = {"com.serfinanzas.prestamos.persistence"})
public class PersistenceConfig {
}
