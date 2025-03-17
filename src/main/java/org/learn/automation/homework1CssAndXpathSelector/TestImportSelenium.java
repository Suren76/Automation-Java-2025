package org.learn.automation.homework1CssAndXpathSelector;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.manager.SeleniumManager;

public class TestImportSelenium {
    public static void main(String[] args) {
        String urlToOpen = "https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.29.0";

        ChromeDriver driver = new ChromeDriver();
        driver.get(urlToOpen);
        driver.quit();
    }
}
