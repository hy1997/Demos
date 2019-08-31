package com.example.demo.autoJob;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPicture {
    public void getHtmlPicture(String httpUrl, String filePath) {
        URL url;
        BufferedInputStream in;
        FileOutputStream file;

        try {
            System.out.println("爬取网络图片");
            // 获取图片名
            String fileName = httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", "");
            // 初始化url对象
            url = new URL(httpUrl);
            // 初始化in对象，也就是获得url字节流
            in = new BufferedInputStream(url.openStream());
            file = new FileOutputStream(new File(filePath + "\\" + fileName));
            int t;
            while ((t = in.read()) != -1) {
                file.write(t);
            }
            file.close();
            in.close();
            System.out.println("图片爬取成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHtmlCode(String httpUrl) throws IOException {
        String content = "";
        URL url = new URL(httpUrl);
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
        return content;
    }

    /**
     * 图片爬取方法
     *
     * @param url
     * @throws IOException
     */
    public void get(String url, String filePath) throws IOException {

        // 定义两个获取网页图片的正则表达式
        String searchImgReg = "(?x)(src|SRC|background|BACKGROUND)=('|\")/?(([\\w-]+/)*([\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";
        String searchImgReg2 = "(?x)(src|SRC|background|BACKGROUND)=('|\")(http://([\\w-]+\\.)+[\\w-]+(:[0-9]+)*(/[\\w-]+)*(/[\\w-]+\\.(jpg|JPG|png|PNG|gif|GIF)))('|\")";

        String content = this.getHtmlCode(url);
        Pattern pattern = Pattern.compile(searchImgReg);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(3));
            this.getHtmlPicture(url + "/" + matcher.group(3), filePath);
        }
        pattern = Pattern.compile(searchImgReg2);
        matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(3));
            this.getHtmlPicture(matcher.group(3), filePath);

        }

    }
}
