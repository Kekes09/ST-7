package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.PrintWriter;

public class Task3 {

    public static void getWeatherForecast() {
        System.setProperty("webdriver.chrome.driver",
                "F:/chromedriver-win64/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        String url = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=56&longitude=44" +
                "&hourly=temperature_2m,rain" +
                "&current=cloud_cover" +
                "&timezone=Europe%2FMoscow" +
                "&forecast_days=1" +
                "&wind_speed_unit=ms";

        try {
            webDriver.get(url);
            Thread.sleep(1500);

            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONObject hourlyUnits = (JSONObject) root.get("hourly_units");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            String tempUnit = (String) hourlyUnits.get("temperature_2m");
            String rainUnit = (String) hourlyUnits.get("rain");

            // Создаём папку result, если нет
            new File("result").mkdirs();

            System.out.println("\n=== Задание 3: Прогноз погоды (Нижний Новгород) ===");
            String header = String.format("| %-3s | %-18s | %-12s | %-12s |",
                    "№", "Дата/время", "Температура", "Осадки (" + rainUnit + ")");
            String separator = "|-----|--------------------|--------------|--------------|";

            System.out.println(header);
            System.out.println(separator);

            PrintWriter writer = new PrintWriter("result/forecast.txt", "UTF-8");
            writer.println("Прогноз погоды на сутки - Нижний Новгород");
            writer.println(header);
            writer.println(separator);

            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                String formattedTime = time.length() > 16 ? time.substring(0, 16) : time;

                double temp = (Double) temperatures.get(i);
                double rain = (Double) rains.get(i);

                String row = String.format("| %-3d | %-18s | %-11s%s | %-11s%s |",
                        i + 1, formattedTime, temp, tempUnit, rain, rainUnit);

                System.out.println(row);
                writer.println(row);
            }
            writer.println(separator);
            writer.close();

            System.out.println("\n✅ Прогноз сохранён в result/forecast.txt");

        } catch (Exception e) {
            System.out.println("Error in Task3");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}