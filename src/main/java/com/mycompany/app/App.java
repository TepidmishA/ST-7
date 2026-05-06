package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try {
            System.out.println("=== Task 1 ===");
            generatePassword(webDriver);
            
            System.out.println("\n=== Task 2 ===");
            Task2.getMyIp(webDriver);
            
            System.out.println("\n=== Task 3 ===");
            Task3.getWeather(webDriver);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }

    // Task 1 implementation
    public static void generatePassword(WebDriver webDriver) {
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            
            String password = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='verybigtext']")))
                .getText();
            
            System.out.println("Generated password: " + password);
            
        } catch (Exception e) {
            System.out.println("Task 1 error: " + e.getMessage());
        }
    }
}