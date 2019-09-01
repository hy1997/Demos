package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        /*String content="";
        URL url = null;
        try {
            url = new URL("https://www.byton.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String input;
        // 如果有数据
        while ((input = reader.readLine()) != null) {
            // 将读取数据赋给content
            content += input;
        }
        // 关闭缓冲区
        reader.close();
        // 返回content
        System.out.println("content"+content);
*/
        SpringApplication.run(DemoApplication.class, args);
    }

}
