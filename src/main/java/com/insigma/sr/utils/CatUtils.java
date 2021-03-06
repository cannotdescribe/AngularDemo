package com.insigma.sr.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * 一个杂物工具类，王辉限定
 * @author 王辉
 */
public class CatUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String[] imageSuffixs = {"jpg", "png", "gif"};

	public static Integer[] intParseArr(String[] str){
		if(str==null||str.length==0){
			return new Integer[0];
		}
		Integer[] end = new Integer[str.length];
		for(int i=0;i<str.length;i++){
			end[i] = Integer.parseInt(str[i]);
		}
		return end;
	}
	public static List<Integer> intParseList(String[] str){
		if(str==null){
			return new ArrayList<Integer>();
		}
		List<Integer> end = new ArrayList<Integer>();
		for(int i=0;i<str.length;i++){
			end.add(Integer.parseInt(str[i]));
		}
		return end;
	}
	public static Integer parseInt(Object str){
		if(str instanceof String){
			String s = (String)str;
			if(!isNumber(s)){
				return null;
			}
			return Integer.parseInt(s);
		}else{
			return (Integer)str;
		}
	}
    public static Float parseFloat(Object str){
        if(str instanceof String){
            String s = (String)str;
            if(!isNumber(s)){
                return null;
            }
            return Float.parseFloat(s);
        }else{
            return (Float)str;
        }
    }
	public static boolean isNumber(String num){
		return num!=null&&!"".equals(num)&&num.matches("\\d*.?\\d*");
	}
	public static boolean timeGap(Date arg, long time){
		Date now = new Date();
		return timeGap(now, arg, time);
	}
	
	public static boolean timeGap(Date src, Date arg, long time){
		if(arg==null||src==null){
			return false;
		}
		long srcMill = src.getTime();
		long argMill = arg.getTime();
		if(srcMill-argMill>time){
			return false;
		}else{
			return true;
		}
	}
	public static int searchContent(int[] vals , Integer val){
		if(val==null){
			return -1;
		}
		for(int i=0;i<vals.length;i++){
			if(vals[i]==val){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isNotEmpty(String s) {
		return isEmpty(s) ? false : true;
	}
	
	public static boolean isEmpty(Object s) {
		return s == null;
	}
	public static boolean isNotEmpty(Object s) {
		return isEmpty(s) ? false : true;
	}
	
	public static String getNow(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}
	public static String getNow(){
		return sdf.format(new Date());
	}
	public static String parse(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof String){
			return (String)obj;
		}else{
			return ((Integer)obj).toString();
		}
	}
	
	public static String forward(String path, int num){
		return forward(path, num, true);
	}
	
	public static String forward(String path, int num, boolean endSeparator){
		int length = path.length();
		int last = path.lastIndexOf("/");
		if(length == last + 1){
			num += 1;
		}
		for(int i=0; i<num-1; i++){
			last = path.lastIndexOf("/", last-1);
		}
		path = path.substring(0, last);
		if(endSeparator){
			return path+"/";
		}else{
			return path;
		}
	}
	
	public static String getProjectPath(){
		return CatUtils.class.getResource("/").getPath();
	}
	
	/**
	 * 在web项目中获取静态资源
	 * @return
	 */
	public static String getWebStaticResource(){
		String projectPath  = forward(CatUtils.getProjectPath(), 1);
		System.out.println("projectPath: "+ projectPath);
		String path = projectPath;
		if(new File(path).exists()){
			if(new File(forward(path, 1)+"images/").exists()){
				return forward(path, 1);
			}
		}
		if(!new File(path + "WEB-INF").exists()){
			projectPath  = CatUtils.forward(CatUtils.getProjectPath(), 2);
			path = projectPath +"src/main/webapp/" ;
			if(!new File(path + "WEB-INF").exists()){
				try {
					throw new FileNotFoundException("没有发现静态资源路径，请自行扩充该函数");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}
	/**
	 * 首字母大写
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
    } 
	/**
	 * 获取set方法
	 * @param fieldName
	 * @return
	 */
	public static String getSetMethodName(String fieldName){
		return "set" + captureName(fieldName);
	}
    /**
     * 获取get方法
     * @param fieldName
     * @return
     */
    public static String getGetMethodName(String fieldName){
        return "get" + captureName(fieldName);
    }


	public static Integer arrayIndexOf(Object[] objs, Object obj){
		for(int i=0;i<objs.length;i++){
			if(objs[i].equals(obj)){
				return i;
			}
		}
		return -1;
	}
	
	public static String imageRename(String pathName){
		int index = pathName.lastIndexOf(".");
		if(index==-1){
			return null;
		}
		String head = pathName.substring(0, index);
		String suffix = pathName.substring(index+1);
		if(arrayIndexOf(imageSuffixs, suffix)!=-1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");
			String date = sdf.format(new Date());
			StringBuilder sb = new StringBuilder();
			sb.append(head);
			sb.append("_");
			sb.append(date);
			sb.append(".");
			sb.append(suffix);
			return sb.toString();
		}
		return null;
	}
	public static String arrGet(String[] arr, Object index){
		Integer indexx = CatUtils.parseInt(index);
		if(indexx == null || arr.length <= indexx ){
			return indexx.toString();
		}else{
			return arr[indexx];
		}
	}
	public static List<Method> getDetailMethods(Class<?> clazz, String methodName){
		List<Method> methods = new ArrayList<Method>();
		methods.addAll(getMethods(clazz, methodName));
		while(!"java.lang.Object".equals(clazz.getSuperclass().getName())){
			clazz = clazz.getSuperclass();
			methods.addAll(getMethods(clazz, methodName));
		}
		return methods;
	}
	
	public static List<Method> getMethods(Class<?> clazz, String methodName){
		List<Method> methods = new ArrayList<Method>();
		Method[] ms = clazz.getDeclaredMethods();
		for(Method method : ms){
			if(methodName.equals(method.getName())){
				methods.add(method);
			}
		}
		return methods;
	}

	public static <T> void setAttribute(T t, String  attrName, Object value){
	    if("powerFactor".equals(attrName)){
            System.out.println(value);
        }
        Class<?> clazz = t.getClass();
        List<Method> methods = getDetailMethods(clazz, getSetMethodName(attrName));
        for(Method m : methods){
            try {
                System.out.println(value.getClass().getName());
                m.invoke(t, value);
                break;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> Object getAttribute(T t, String  attrName){
        Class<?> clazz = t.getClass();
        List<Method> methods = getDetailMethods(clazz, getGetMethodName(attrName));
        for(Method m : methods){
            try {
                return m.invoke(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void createFolderIfInexistent(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }
}
