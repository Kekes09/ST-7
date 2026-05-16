package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                 "F:/chromedriver-win64/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            Thread.sleep(2000);

            WebElement passwordElement = webDriver.findElement(By.id("presult"));
            String password = passwordElement.getText();

            System.out.println("=== Задание 1 ===");
            System.out.println("Сгенерированный пароль: " + password);

        } catch (Exception e) {
            System.out.println("Error in Task1");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }

        Task2.getIpAddress();
        Task3.getWeatherForecast();
    }
}