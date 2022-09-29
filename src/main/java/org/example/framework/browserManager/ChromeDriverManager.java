package org.example.framework.browserManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createDriver(){
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/mac/chromedriver");
        driver = new ChromeDriver();
    }
}
