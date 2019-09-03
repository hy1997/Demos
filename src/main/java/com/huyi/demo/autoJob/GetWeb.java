package com.huyi.demo.autoJob;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetWeb {private int webDepth = 5; // 爬虫深度
    private int intThreadNum = 1; // 线程数
    private String strHomePage = ""; // 主页地址
    private String myDomain; // 域名
    private String fPath = "CSDN"; // 储存网页文件的目录名
    private ArrayList<String> arrUrls = new ArrayList<String>(); // 存储未处理URL
    private ArrayList<String> arrUrl = new ArrayList<String>(); // 存储所有URL供建立索引
    private Hashtable<String, Integer> allUrls = new Hashtable<String, Integer>(); // 存储所有URL的网页号
    private Hashtable<String, Integer> deepUrls = new Hashtable<String, Integer>(); // 存储所有URL深度
    private int intWebIndex = 0; // 网页对应文件下标，从0开始
    private long startTime;
    private int webSuccessed = 0;
    private int webFailed = 0;

    public GetWeb(String s) {
        this.strHomePage = s;
    }

    public GetWeb(String s, int i) {
        this.strHomePage = s;
        this.webDepth = i;
    }

    public synchronized void addWebSuccessed() {
        webSuccessed++;
    }

    public synchronized void addWebFailed() {
        webFailed++;
    }

    public synchronized String getAUrl() {
        String tmpAUrl = arrUrls.get(0);
        arrUrls.remove(0);
        return tmpAUrl;
    }

    public synchronized String getUrl() {
        String tmpUrl = arrUrl.get(0);
        arrUrl.remove(0);
        return tmpUrl;
    }

    public synchronized Integer getIntWebIndex() {
        intWebIndex++;
        return intWebIndex;
    }



    /**
     * 由用户提供的域名站点开始，对所有链接页面进行抓取
     */
    public void getWebByHomePage() {
        startTime = System.currentTimeMillis();
        this.myDomain = getDomain();
        if (myDomain == null) {
            System.out.println("Wrong input!");
            return;
        }

        System.out.println("Homepage = " + strHomePage);
        System.out.println("Domain = " + myDomain);
        arrUrls.add(strHomePage);
        arrUrl.add(strHomePage);
        allUrls.put(strHomePage, 0);
        deepUrls.put(strHomePage, 1);

        File fDir = new File(fPath);
        if (!fDir.exists()) {
            fDir.mkdir();
        }

        System.out.println("开始工作");
        String tmp = getAUrl(); // 取出新的URL
        this.getWebByUrl(tmp, allUrls.get(tmp) + ""); // 对新URL所对应的网页进行抓取
        int i = 0;
        for (i = 0; i < intThreadNum; i++) {
            new Thread(new Processer(this)).start();
        }
        while (true) {
            if (arrUrls.isEmpty() && Thread.activeCount() == 1) {
                long finishTime = System.currentTimeMillis();
                long costTime = finishTime - startTime;
                System.out.println("\n\n\n\n\n完成");
                System.out.println(
                        "开始时间 = " + startTime + "   " + "结束时间 = " + finishTime + "   " + "爬取总时间= " + costTime + "ms");
                System.out.println("爬取的URL总数 = " + (webSuccessed + webFailed) + "   成功的URL总数: " + webSuccessed
                        + "   失败的URL总数: " + webFailed);
                String strIndex = "";
                String tmpUrl = "";
                while (!arrUrl.isEmpty()) {
                    tmpUrl = getUrl();
                    strIndex += "Web depth:" + deepUrls.get(tmpUrl) + "   Filepath: " + fPath + "/web"
                            + allUrls.get(tmpUrl) + ".htm" + "url:" + tmpUrl + "\n\n";
                }
                System.out.println(strIndex);
                try {
                    PrintWriter pwIndex = new PrintWriter(new FileOutputStream("fileindex.txt"));
                    pwIndex.println(strIndex);
                    pwIndex.close();
                } catch (Exception e) {
                    System.out.println("生成索引文件失败!");
                }
                break;
            }
        }
    }
    /**
     * 对后续解析的网站进行爬取
     *
     * @param strUrl
     * @param fileIndex
     */
    public void getWebByUrl(String strUrl, String fileIndex) {
        try {
            System.out.println("通过URL得到网站: " + strUrl);

            URL url = new URL(strUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            InputStream is = null;
            is = url.openStream();
            String filename = strUrl.replaceAll("/", "_");
            filename = filename.replace(":", ".");
            if (filename.indexOf("*") > 0) {
                filename = filename.replaceAll("/*", ".");
            }
            if (filename.indexOf("?") > 0) {
                filename = filename.replaceAll("/?", ".");
            }
            if (filename.indexOf("\"") > 0) {
                filename = filename.replaceAll("\"", ".");
            }
            if (filename.indexOf(">") > 0) {
                filename = filename.replaceAll(">", ".");
            }
            if (filename.indexOf("<") > 0) {
                filename = filename.replaceAll("<", ".");
            }
            if (filename.indexOf("|") > 0) {
                filename = filename.replaceAll("|", ".");
            }
            String filePath = fPath + "\\" + filename;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
          /*  JDBCHelper helper = new JDBCHelper();
            helper.insertFilePath(filename, filePath, strUrl);*/
            System.out.println("*********"+filename);
            System.out.println("*********"+filePath);
            System.out.println("*********"+strUrl);

            GetPicture getp = new GetPicture();
            getp.get(strUrl, filePath);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            String rLine = null;
            String tmp_rLine = null;
            while ((rLine = bReader.readLine()) != null) {
                tmp_rLine = rLine;
                int str_len = tmp_rLine.length();
                if (str_len > 0) {
                    sb.append("\n" + tmp_rLine);
                    if (deepUrls.get(strUrl) < webDepth)
                        getUrlByString(tmp_rLine, strUrl);
                }
                tmp_rLine = null;
            }
            is.close();
            System.out.println("获取网站成功 " + strUrl);
            addWebSuccessed();
        } catch (Exception e) {
            System.out.println("获取网站失败，请检查URL是否存在 " + strUrl);
            addWebFailed();
        }
    }
    /**
     * 判断用户所提供URL是否为域名地址
     *
     * @return
     */
    public String getDomain() {
        String reg = "(?<=http\\://[a-zA-Z0-9]{0,100}[.]{0,1})[^.\\s]*?\\.(com|cn|net|org|biz|info|cc|tv|edu)";
        Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strHomePage);
        boolean blnp = m.find();
        if (blnp == true) {
            return m.group(0);
        }
        return null;
    }

    /**
     * 解析新的网页，提取其中含有的链接信息
     *
     * @param inputArgs
     * @param strUrl
     */
    public void getUrlByString(String inputArgs, String strUrl) {
        String tmpStr = inputArgs;
        String regUrl = "(?<=(href=)[\"]?[\']?)[http://][^\\s\"\'\\?]*(" + myDomain + ")[^\\s\"\'>]*";
        Pattern p = Pattern.compile(regUrl, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tmpStr);
        boolean blnp = m.find();
        while (blnp == true) {
            if (!allUrls.containsKey(m.group(0))) {
                System.out.println("Find a new url,depth:" + (deepUrls.get(strUrl) + 1) + " " + m.group(0));
                arrUrls.add(m.group(0));
                arrUrl.add(m.group(0));
                allUrls.put(m.group(0), getIntWebIndex());
                deepUrls.put(m.group(0), (deepUrls.get(strUrl) + 1));
            }
            tmpStr = tmpStr.substring(m.end(), tmpStr.length());
            m = p.matcher(tmpStr);
            blnp = m.find();
        }
    }

    /**
     * @author amuxia 另一个独立的爬取线程
     */
    class Processer implements Runnable {
        GetWeb gw;

        public Processer(GetWeb g) {
            this.gw = g;
        }

        public void run() {
            while (!arrUrls.isEmpty()) {
                String tmp = getAUrl();
                getWebByUrl(tmp, allUrls.get(tmp) + "");
            }
        }
    }

}
