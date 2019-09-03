package com.huyi.demo.autoJob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class DownloadImage {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        download("https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1567266378295&amp;di=1c5f665e46fb448945ef9bd7e1cc876a&amp;imgtype=0&amp;src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201406%2F18%2F20140618225004_eT8Bf.jpeg","E:\\upload\\SDSPage");
    }

    public static String download(String urlPath,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlPath);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        int randomNo=(int)(Math.random()*1000000);
        String filename=urlPath.substring(urlPath.lastIndexOf("/")+1,urlPath.length());//获取服务器上图片的名称
        filename=new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+randomNo+filename;//时间+随机数防止重复
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        String virtualPath="/upload/SDSPage/"+filename;//存入数据库的虚拟路径
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
        return virtualPath;
    }
}
