package com.mornaeldernar.api.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
public class Selenium {
    private WebDriver driver;
    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disabled-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void teardown(){
        if(driver!=null){
            driver.quit();
        }
    }
    @Test
    public void verifylocalhostTest(){
        driver.get("http://localhost:8988/login");
        assertEquals(driver.getTitle(),"");
    }
}
