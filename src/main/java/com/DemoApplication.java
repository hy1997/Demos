package com;

import com.google.common.primitives.Bytes;
import com.huyi.demo.interceptor.WebMvcConfg;
import com.huyi.redis.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@ServletComponentScan(basePackages = "huyi.demo.listener.RequestListener")
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        RedisUtil redisUtil = context.getBean(RedisUtil.class);
        WebMvcConfg loginInterceptor = context.getBean(WebMvcConfg.class);
        System.err.println("redisUtil:" + redisUtil);
        System.err.println("loginInterceptor:" + loginInterceptor);
        byte[] bytes = "ss".getBytes("GBK");
        String string = new String(bytes);


    }

    public void testPerson() throws Exception {
        //添加人员
        BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\MeiGong\\Desktop\\a.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        byte[] encode = Base64.getEncoder().encode(imageInByte);
        byte[] bytes = "ssss".getBytes("UTF-8");
        String strin = new String(bytes, "UTF-8");



    }


}
