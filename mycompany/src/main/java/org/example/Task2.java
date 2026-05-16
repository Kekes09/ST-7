package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {

    public static void getIpAddress() {
        System.setProperty("webdriver.chrome.driver",
                "F:/chromedriver-win64/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get("https://api.ipify.org/?format=json");
            Thread.sleep(1000);

            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            String ipAddress = (String) obj.get("ip");

            System.out.println("\n=== Задание 2 ===");
            System.out.println("Ваш IP-адрес: " + ipAddress);

        } catch (Exception e) {
            System.out.println("Error in Task2");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}