package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
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
