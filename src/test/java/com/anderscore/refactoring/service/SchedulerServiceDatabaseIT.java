package com.anderscore.refactoring.service;

import com.anderscore.refactoring.config.TestWithDatabaseConfig;
import com.anderscore.refactoring.data.Scheduler;
import com.anderscore.refactoring.extension.DbContainerExtension;
import com.anderscore.refactoring.service.SchedulerService;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

@SpringJUnitConfig(TestWithDatabaseConfig.class)
@ActiveProfiles("test")
// Activate for Testcontainers
//@ExtendWith(DbContainerExtension.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class SchedulerServiceDatabaseIT {

    @Inject
    private SchedulerService schedulerService;

    @Test
    @FlywayTest(locationsForMigrate = {"db/scheduler_data"})
    public void findAll() {
        List<Scheduler> result = schedulerService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void store() {
        Scheduler scheduler = new Scheduler();
        scheduler.setName("ElBarto");
        scheduler.setCron("45 23 * * 6");

        schedulerService.store(scheduler);

        Optional<Scheduler> saveScheduler = schedulerService.findByName("ElBarto");

        assertTrue(saveScheduler.isPresent());
        assertEquals(saveScheduler.get().getName(), scheduler.getName());
        assertEquals(saveScheduler.get().getCron(), scheduler.getCron());
    }
}