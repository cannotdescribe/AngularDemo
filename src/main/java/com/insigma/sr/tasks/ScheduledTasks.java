package com.insigma.sr.tasks;

import com.insigma.sr.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

//开启定时任务
@Slf4j
@Component("scheduledTasks")
public class ScheduledTasks {

    public ScheduledTasks(){
        log.info("初始化component");
    }

    private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;

    @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
    public void testFixDelay() {
        try {
            WebSocketServer.sendInfo("===fixedDelay: 第"+fixedDelayCount+"次执行方法");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.info("===fixedDelay: 第{}次执行方法", fixedDelayCount++);
    }

    @Scheduled(fixedRate = 5000)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
    public void testFixedRate() {
//        log.info("===fixedRate: 第{}次执行方法", fixedRateCount++);
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)   //initialDelay = 1000表示延迟1000ms执行第一次任务
    public void testInitialDelay() {
//        log.info("===initialDelay: 第{}次执行方法", initialDelayCount++);
    }

    @Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void testCron() {
//        log.info("===initialDelay: 第{}次执行方法", cronCount++);
    }

}