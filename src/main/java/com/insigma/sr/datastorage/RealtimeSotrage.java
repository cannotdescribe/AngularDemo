package com.insigma.sr.datastorage;

import com.insigma.sr.bean.RealtimeAlarmBean;
import com.insigma.sr.mock.NameMock;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class RealtimeSotrage {
    private String[] tagIsids = new String[]{
            "100001001",
            "100001002",
            "100001003",
            "100001004",
            "100001005",
            "100001006",
            "100001007",
            "100001008",
            "100001009",
            "100001010",
            "100001011",
            "100001012",

            "100002013",
            "100002014",
            "100002015",
            "100002016",
            "100002017",
            "100002018",
            "100002019",
            "100002020",
            "100002021",
            "100002022",
    };

    private Map<String, RealtimeAlarmBean> store = new HashMap<String, RealtimeAlarmBean>();

    @PostConstruct
    public void init(){
        for(String tagIsid : tagIsids){
            RealtimeAlarmBean realtimeAlarmBean = new RealtimeAlarmBean();
            realtimeAlarmBean.setTagIsid(tagIsid);
            realtimeAlarmBean.setAlarmDesc(tagIsid+"发送告警");

            realtimeAlarmBean.setDvName("");
            store.put(tagIsid, realtimeAlarmBean);

            System.out.println(NameMock.name);
        }
    }


}
