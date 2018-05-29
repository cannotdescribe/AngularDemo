package com.insigma.sr.controller;

import com.insigma.sr.bean.RealtimeAlarmBean;
import com.insigma.sr.bean.ResultEndBean;
import com.insigma.sr.datastorage.RealtimeSotrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/inIoT/realtime")
public class RealtimeAlarmController {

    @Autowired
    private RealtimeSotrage realtimeSotrage;

    @ResponseBody
    @RequestMapping("/alarm/init")
    public ResultEndBean realtime(){
        return new ResultEndBean(realtimeSotrage.getInit());
    }

}
