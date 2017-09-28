package test.java.com.insigma.mapload.edushi;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MapperLoader {
    public static File baseFile(String basePath){
        String dir = basePath.substring(0, basePath.lastIndexOf("/"));
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        File file2 = new File(basePath);
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file2;
    }
    public static void createFile(BufferedInputStream fis, String basePath) throws Exception {
        File file = baseFile(basePath);
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buff = new byte[1024];
        int a = 0;
        while((a = fis.read(buff))!= -1){
            bos.write(buff, 0 , a);
        }
        bos.close();
    }
    public static void main(String[] arg0) throws Exception {
        String path = "src/main/webapp/map/eds/";
        //这个是紫荆港的
        Map<String, List<String>> m = MapPointCreate.markAllInfo(new String[]{"5,3"},12,6);
        //这个是杭州南站的
        //Map<String, List<String>> m = MapPointCreate.markAllInfo(new String[]{"13,31"},12,6);
        Set<Map.Entry<String, List<String>>> sm = m.entrySet();
        Scanner scan = new Scanner(System.in);
        System.out.println("开始下载,请等待程序执行结束。");
        int size = 0;
        for (Map.Entry<String, List<String>> entry: sm){
            List<String> value = entry.getValue();
            String key = entry.getKey();
            size += value.size();
        }
        System.out.println("检测到共有 "+size+" 条数据，请按回车开始下载!");
        scan.nextLine();
        System.out.println("开始下载...");
        for (Map.Entry<String, List<String>> entry: sm){
            String key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println(key + "  "+value.size());
            String pngUrl = MapPointCreate.urlStr + key +"/";
            for(String v : value){
                String p = pngUrl+ v+".png";
                URL url = new URL(p);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream fis = new BufferedInputStream(conn.getInputStream());
                createFile(fis, path+key+"/"+v+".png");
            }
        }
    }
}
