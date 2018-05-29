package com.insigma.sr.datastorage;

import com.insigma.sr.bean.RealtimeAlarmBean;
import com.insigma.sr.mock.NameMock;
import com.insigma.sr.utils.CatUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class RealtimeSotrage {
    private String[] tagIsids = new String[]{
            "1000010001000001",
            "1000010001000002",
            "1000010001000003",
            "1000010001000004",
            "1000010001000005",
            "1000010001000006",
            "1000010001000007",
            "1000010001000008",
            "1000010001000009",
            "1000010001000010",
            "1000010001000011",
            "1000010001000012",

            "1000010002000001",
            "1000010002000002",
            "1000010002000003",
            "1000010002000004",
            "1000010002000005",
            "1000010002000006",
            "1000010002000007",
            "1000010002000008",
            "1000010002000009",
            "1000010002000010",


            "1000020001000001",
            "1000020001000002",
            "1000020001000003",
            "1000020001000004",
            "1000020001000005",
            "1000020001000006",
            "1000020001000007",
            "1000020001000008",
            "1000020001000009",
            "1000020001000010",
            "1000020001000011",
            "1000020001000012",

            "1000020002000001",
            "1000020002000002",
            "1000020002000003",
            "1000020002000005",
            "1000020002000006",
            "1000020002000007",
            "1000020002000008",
            "1000020002000009",
            "1000020002000010",
    };

    private Map<String, RealtimeAlarmBean> store = new HashMap<String, RealtimeAlarmBean>();

    private List<RealtimeAlarmBean> storeArray = new ArrayList<>();

    private Random random = new Random();


    @PostConstruct
    public void init(){

        Map<String, String> st = new HashMap<String, String>();

        Map<String, String> dv = new HashMap<String, String>();

        int index = 0;
        for(String tagIsid : tagIsids){
            String stName = null;
            String dvName = null;
            String stIsid = tagIsid.substring(0, 6);
            String dvIsid = tagIsid.substring(0, 10);

            if(st.containsKey(stIsid)){
                stName = st.get(stIsid);
            }else{
                stName = NameMock.getName();
                st.put(stIsid, stName);
            }

            if(dv.containsKey(dvIsid)){
                dvName = dv.get(dvIsid);
            }else{
                dvName = NameMock.getName();
                dv.put(dvIsid, dvName);
            }

            RealtimeAlarmBean realtimeAlarmBean = new RealtimeAlarmBean();
            realtimeAlarmBean.setTagIsid(tagIsid);
            realtimeAlarmBean.setNodeIsid(stIsid);
            realtimeAlarmBean.setDvIsid(dvIsid);
            realtimeAlarmBean.setAlarmLevel(randomAlarmLevel());
            realtimeAlarmBean.setAlarmTime(CatUtils.getNow());
            realtimeAlarmBean.setAlarmDesc(tagIsid+"发送告警");
            realtimeAlarmBean.setNodeName(stName);
            realtimeAlarmBean.setDvName(dvName);
            realtimeAlarmBean.setTagName(NameMock.getName());
            realtimeAlarmBean.setCodeValue(randomValue());

            storeArray.add(realtimeAlarmBean);
//            store.put(tagIsid, realtimeAlarmBean);
        }
    }

    private String randomValue(){
        return String.valueOf(random.nextInt(20)+10);
    }
    private Integer randomAlarmLevel(){
        return random.nextInt(3)+1;
    }

    public List<RealtimeAlarmBean> getInit(){
        return storeArray;
    }


    public RealtimeAlarmBean realtimeAlarm(){
        RealtimeAlarmBean rab = storeArray.get(random.nextInt(tagIsids.length));
        rab.setIsRecover(random.nextInt(2));
        rab.setCodeValue(randomValue());
        rab.setAlarmLevel(randomAlarmLevel());
        rab.setAlarmTime(CatUtils.getNow());
        return rab;
    }
}

