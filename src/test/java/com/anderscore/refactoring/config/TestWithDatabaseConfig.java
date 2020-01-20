package com.anderscore.refactoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.anderscore.refactoring.data.SchedulerRepository;

@Configuration
@Profile("test")
@Import({TestWithoutDatabaseConfig.class, HsqlPersistenceConfig.class})
// Activate for Testcontainers
//@Import({TestWithoutDatabaseConfig.class, TestcontainersPersistenceConfig.class})
@EnableJpaRepositories(basePackageClasses = SchedulerRepository.class)
public class TestWithDatabaseConfig {
}