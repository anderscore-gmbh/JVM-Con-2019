package com.anderscore.refactoring.config;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.anderscore.refactoring.csv.CsvExporter;
import com.anderscore.refactoring.data.SchedulerRepository;
import com.anderscore.refactoring.service.SchedulerService;

@Configuration
@Profile("test")
@ComponentScan(basePackageClasses = {SchedulerService.class, CsvExporter.class})
public class TestWithoutDatabaseConfig {
	
	@Bean
	public SchedulerRepository schedulerRepository() {
		return mock(SchedulerRepository.class);
	}
}