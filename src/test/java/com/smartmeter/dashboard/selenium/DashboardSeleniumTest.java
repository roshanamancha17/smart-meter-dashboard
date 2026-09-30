package com.smartmeter.dashboard.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DashboardSeleniumTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testAddMeterReadingFlow() {
        driver.get("http://localhost:" + port + "/");
        assertTrue(driver.getTitle().contains("Smart Meter"));

        WebElement meterInput = driver.findElement(By.name("meterId"));
        WebElement consumptionInput = driver.findElement(By.name("consumptionKwh"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        meterInput.sendKeys("MTR-TEST-101");
        consumptionInput.sendKeys("150.0");
        submitBtn.click();

        WebElement alertBadge = driver.findElement(By.xpath("//span[text()='ALERT']"));
        assertTrue(alertBadge.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}