package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 
{
    public static void getMyIp(WebDriver webDriver) {
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            
            // Wait for JSON response
            WebElement preElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("pre")));
            String jsonText = preElement.getText();
            
            // Parse JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonText);
            String ip = (String) jsonObject.get("ip");
            
            System.out.println("IP address: " + ip);
            
        } catch (Exception e) {
            System.out.println("Task 2 error: " + e.getMessage());
        }
    }
}