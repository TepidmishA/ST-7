package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Task3 
{
    private static final String URl = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
    private static final String FILE_NAME = "result/forecast.txt";

    public static void getWeather(WebDriver webDriver) {
        try {
            webDriver.get(URl);

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            
            // Wait for JSON response
            WebElement preElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("pre")));
            String jsonText = preElement.getText();
            
            // Parse JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonText);
            
            JSONObject hourly = (JSONObject) jsonObject.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");
            
            // Write to file
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                writer.println("+----+------------------+------------------+-------------+");
                writer.println("| №  | Дата/время       | Температура (°C) | Осадки (мм) |");
                writer.println("+----+------------------+------------------+-------------+");
                
                for (int i = 0; i < time.size(); i++) {
                    String dateTime = (String) time.get(i);
                    String temp = String.valueOf(temperature.get(i));
                    String rainValue = String.valueOf(rain.get(i));
                    
                    writer.printf("| %-2d | %-16s | %-16s | %-11s |\n", 
                        (i + 1), dateTime, temp, rainValue);
                }
                
                writer.println("+----+------------------+------------------+-------------+");
            }
            
            System.out.println("Weather forecast saved to: " + FILE_NAME);
            
        } catch (Exception e) {
            System.out.println("Task 3 error: " + e.getMessage());
        }
    }
}