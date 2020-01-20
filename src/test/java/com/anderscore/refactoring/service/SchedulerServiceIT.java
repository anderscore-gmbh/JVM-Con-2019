package com.anderscore.refactoring.service;

import com.anderscore.refactoring.config.TestWithoutDatabaseConfig;
import com.anderscore.refactoring.data.Scheduler;
import com.anderscore.refactoring.service.SchedulerService;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static java.util.Calendar.DECEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.GregorianCalendar;

import javax.inject.Inject;

@SpringJUnitConfig(TestWithoutDatabaseConfig.class)
@ActiveProfiles("test")
public class SchedulerServiceIT {

    @Inject
    private SchedulerService schedulerService;

    @Test
    public void testConvertToCsv() {
    	Scheduler scheduler = new Scheduler();
    	scheduler.setName("TestScheduler");
    	scheduler.setCron("45 23 * * 6");
    	scheduler.setCreatedAt(new GregorianCalendar(2019, DECEMBER, 10).getTime());
    	scheduler.setUpdatedAt(new GregorianCalendar(2019, DECEMBER, 11).getTime());
    	
    	String expected = "Name;Cron;Erstellt am;Aktualisiert am\n" + 
    			"TestScheduler;45 23 * * 6;Tue Dec 10 00:00:00 CET 2019;Wed Dec 11 00:00:00 CET 2019";
    	
        String actual = schedulerService.convertToCsv(scheduler);

        assertEquals(expected, actual);
    }
}