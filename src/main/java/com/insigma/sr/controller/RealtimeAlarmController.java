package com.insigma.sr.controller;

import com.insigma.sr.bean.ResultEndBean;
import com.insigma.sr.datastorage.RealtimeSotrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realtime")
public class RealtimeAlarmController {

    @Autowired
    private RealtimeSotrage realtimeSotrage;

    @ResponseBody
    @RequestMapping("/alarm/init")
    public ResultEndBean realtime(){
        return new ResultEndBean(realtimeSotrage.getInit());
    }

}
