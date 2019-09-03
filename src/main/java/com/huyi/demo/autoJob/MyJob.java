package com.huyi.demo.autoJob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GetWeb gw = new GetWeb("http://www.byton.com");
        gw.getWebByHomePage();
        System.out.println("end  My Job：" + LocalDateTime.now());

    }
}
