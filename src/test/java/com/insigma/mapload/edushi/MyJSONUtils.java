package test.java.com.insigma.mapload.edushi;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyJSONUtils {
    public static void parse(){
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, String> has = new HashMap<String, String>();
        has.put("a1","a1");
        has.put("a2","a2");
        map.put("msg", has);
        JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("输出的结果是：" + jsonObject);
    }

    public static void main(String[] args) {
        parse();
    }
}
