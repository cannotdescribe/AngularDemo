package com.insigma.sr.bean;

import lombok.Data;

@Data
public class RealtimeAlarmBean {
    private String nodeIsid;
    private String nodeName;
    private String dvIsid;
    private String dvName;
    private String tagIsid;
    private String tagName;
    private String codeValue;
    private Integer alarmLevel;
    private String alarmDesc;
    private String alarmTime;

    private Integer isRecover;
}
