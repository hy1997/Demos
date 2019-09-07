package com.huyi.demo.autoJob;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class GetStatic {

    public String patch;

    public GetStatic(String patch) {
        this.patch = patch;
    }

    public void getUrl() throws MalformedURLException {
        URL url = new URL(patch);
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Map<String, List<String>> requestProperties = connection.getRequestProperties();

            for (Map.Entry<String, List<String>> entry : requestProperties.entrySet()) {
                System.out.println(entry.getKey() + "--->" + entry.getValue());
            }

            br = new BufferedReader(new InputStreamReader(inputStream = url.openStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(br.readLine());
                sb.append('\r' + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                br.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sb.toString());

    }

    public void HttpRequest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/login");

        WebElement elemUsername = driver.findElement(By.id("login_field"));
        WebElement elemPassword = driver.findElement(By.id("password"));
        WebElement btn = driver.findElement(By.cssSelector("input[class='btn btn-primary btn-block']"));
        elemUsername.sendKeys("***");
        elemPassword.sendKeys("***");
        btn.submit();

    }

}
