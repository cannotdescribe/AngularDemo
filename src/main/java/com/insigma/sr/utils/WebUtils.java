package main.java.com.insigma.sr.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class WebUtils {

    public static void responseFile(HttpServletResponse response, String fileName){
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    }

    public static void download(String path, HttpServletResponse response){
        String[] pp = path.split("/");
        String fileName = pp[pp.length-1];
        File file = new File(path);
        responseFile(response, fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 给实体类和他的父类注入属性，要求有多赢的set方法，否则无法注入
     * @param paramName
     * @param value
     * @param instance
     * @param clazz
     * @throws Exception
     */
    private static <T> void injectAttr(String paramName, String value, T instance, Class<T> clazz) throws Exception{
        if(clazz != null){
            try{
                if(CatUtils.isNumber(value)){
                    Method method = clazz.getDeclaredMethod(CatUtils.getSetMethodName(paramName), Integer.class);
                    method.invoke(instance, Integer.parseInt(value));
                }else{
                    Method method = clazz.getDeclaredMethod(CatUtils.getSetMethodName(paramName), String.class);
                    method.invoke(instance, value);
                }
            } catch (NoSuchMethodException e) {
                injectAttr(paramName, value, instance, clazz.getSuperclass());
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 能把request中的所有参数映射为一个实体类，实体类的属性目前仅仅支持String，Integer属性
     * @param request 这个不用我说了吧
     * @param clazz 实体类的 Class
     * @return 返回实体类
     * @throws Exception
     */
    public static <T> T requestMapping(HttpServletRequest request, Class<T> clazz) throws Exception{
        T instance = clazz.newInstance();
        Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paramName = paramNames.nextElement();
            String value = request.getParameter(paramName);
            injectAttr(paramName, value, instance, clazz);
        }
        return instance;
    }



}
