package com.huyi;

import com.huyi.demo.autoJob.GetStatic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Demo {

    public static void main(String[] args) throws IOException {
        GetStatic getStatic = new GetStatic("http://www.baidu.com");
        getStatic.HttpRequest();
    }
}
