package com.torusware.ci.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ExampleTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new RemoteWebDriver(new URL(System.getProperty("seleniumUrl")),
                new DesiredCapabilities(System.getProperty("seleniumBrowser"), "", Platform.ANY));
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNginxWelcomePage() throws Exception {
        driver.get(System.getProperty("testUrl"));
        Thread.sleep(1000);
        assertEquals("Welcome to nginx!", driver.getTitle());
    }
}
