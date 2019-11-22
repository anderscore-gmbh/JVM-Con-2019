package com.anderscore.refactoring.frontend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.anderscore.refactoring.extension.DbContainerExtension;
import com.anderscore.refactoring.extension.WebDriverContainerExtension;
import com.anderscore.refactoring.resolver.ServletContainerContextParameterResolver;
import com.anderscore.refactoring.resolver.WebDriverParameterResolver;
import com.anderscore.refactoring.util.ServletContainerContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//tag::WekanSchedulerTest[]
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(DbContainerExtension.class)
@ExtendWith(WebDriverContainerExtension.class)
@ExtendWith(ServletContainerContextParameterResolver.class)
@ExtendWith(WebDriverParameterResolver.class)
public class WekanSchedulerTest {

  @Test
  public void createScheduler(ServletContainerContext context, RemoteWebDriver webDriver) {
      webDriver.get(context.getHttpUrl());
      
      // SchedulerOverviewPage
      assertEquals("Scheduler", webDriver.findElement(By.tagName("h1")).getText());
      webDriver.findElement(By.id("new")).click();
      
      // SchedulerCreationPage
      assertEquals("Scheduler anlegen", webDriver.findElement(By.tagName("h1")).getText());
      
      WebElement input = webDriver.findElement(By.id("name"));
      input.sendKeys("TestScheduler");
      
      input = webDriver.findElement(By.id("cron"));
      input.sendKeys("45 23 * * 6");
      
      input.submit();
      
      // SchedulerOverviewPage
      assertEquals("Scheduler", webDriver.findElement(By.tagName("h1")).getText());
      assertEquals("1", webDriver.findElement(By.cssSelector("td:nth-child(1) span")).getText());
      assertEquals("TestScheduler", webDriver.findElement(By.cssSelector("td:nth-child(2) span")).getText());
      assertEquals("45 23 * * 6", webDriver.findElement(By.cssSelector("td:nth-child(3) span")).getText());
  }
}
//tag::WekanSchedulerTest[]