package com.insigma.sr.tasks;

import com.insigma.sr.datastorage.RealtimeSotrage;
import com.insigma.sr.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

//开启定时任务
@Slf4j
@Component("scheduledTasks")
public class ScheduledTasks {

    @Autowired
    private RealtimeSotrage realtimeSotrage;

    @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
    public void testFixDelay() {
        try {
            WebSocketServer.sendInfo(JSONObject.fromObject(realtimeSotrage.realtimeAlarm()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.info("===fixedDelay: 第{}次执行方法", fixedDelayCount++);
    }

    @Scheduled(fixedRate = 500)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
    public void testFixedRate() {
    }


}