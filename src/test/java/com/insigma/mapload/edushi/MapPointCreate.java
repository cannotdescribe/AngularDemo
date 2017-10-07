package com.insigma.mapload.edushi;

import java.util.*;

public class MapPointCreate {
    public static final int b = 2;
    public static final int tier = 3;
    public static final String urlStr = "http://npic2.edushi.com/cn/hz/zh-chs/mappic/";
    public static final String PNG = "png";

    //14,31.png
    public static void main(String[] args) throws Exception {

    }

    public static Map<String, List<String>> markAllInfo(String[] maps){
        return markAllInfo(maps,1,1);
    }

    public static Map<String, List<String>> markAllInfo(String[] maps, int x, int y){
        Map<String, List<String>> d = new HashMap<String, List<String>>();
        int num = 0;
        Integer[] center = null;

        if(maps.length==1){
            center =getInteger(maps);
        }else if(maps.length==2){
            center = getCenter(maps);
        }else{
            try {
                throw new IllegalAccessException("请输入长度少于两位的数组，这玩意好求个中间值。");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for(int i=tier; i>=0; i--){
            d.put(PNG+i, allInfo(center, num, x , y));
            num = num + 1;
            center[0] = center[0]*2;
            center[1] = center[1]*2;
        }
        return d;
    }

    public static List<String> allInfo(Integer[] center, int num, int x, int y) {
        double numX = x*Math.pow(b, num);
        double numY = y*Math.pow(b, num);
        double beginX = center[0] - numX/2;
        double endX = center[0] + numX/2;
        double beginY = center[1] - numY/2;
        double endY = center[1] + numY/2;
        List<String> allInfo = new ArrayList<String>();
        for(double i=beginX; i<endX; i++){
            for(double j=beginY; j<endY; j++){
                if(num==0){
                    allInfo.add((int)i+","+(int)j);
                }else{
                    allInfo.add((int)(i-Math.pow(b, num-1))+","+(int)(j));
                }
            }
        }
        return allInfo;
    }

    public static Integer[] getCenter(String[] maps){
        String[] begin = maps[0].split(",");
        String[] end = maps[1].split(",");
        Integer beginX = Integer.parseInt(begin[0]);
        Integer beginY = Integer.parseInt(begin[1]);
        Integer endX = Integer.parseInt(end[0]);
        Integer endY = Integer.parseInt(end[1]);
        int x = (endX + beginX)/2;
        int y = (endY + beginY)/2;
        return new Integer[]{x, y};
    }
    public static Integer[] getInteger(String[] maps){
        String[] point = maps[0].split(",");
        Integer x = Integer.parseInt(point[0]);
        Integer y = Integer.parseInt(point[1]);
        return new Integer[]{x,y};
    }
}
