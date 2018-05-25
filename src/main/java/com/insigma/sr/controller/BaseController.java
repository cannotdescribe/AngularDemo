package com.insigma.sr.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;

@Slf4j
@Controller
public class BaseController {
    public BaseController(){
        log.info("BaseController");
    }
    @ResponseBody
    @RequestMapping("/add")
    public void add(@RequestParam(value = "ilPath", required = false) MultipartFile file, HttpServletRequest request) throws Exception{

        String webapp = com.insigma.sr.utils.CatUtils.getWebStaticResource();
        StringBuilder sb = new StringBuilder();
        sb.append(webapp.substring(0, webapp.length()-1));
        sb.append("/");

        File newImage = new File(sb.toString());
        if(!newImage.exists()){
            newImage.mkdir();
        }
        file.transferTo(newImage);
    }

    @RequestMapping("/{ilPath}")
    public String puck(@PathVariable("ilPath") String ilPath){
    	return ilPath;
    }
    @RequestMapping("/angular/{ilPath}")
    public String dynamic(@PathVariable("ilPath") String ilPath) throws Exception{
        return "/angular/"+ilPath;
    }
    
    @RequestMapping("/vue/{ilPath}")
    public String vue(@PathVariable("ilPath") String ilPath) throws Exception{
        return "/vue/"+ilPath;
    }
    @RequestMapping("/demo/{ilPath}")
    public String demo(@PathVariable("ilPath") String ilPath) throws Exception{
        return "/demo/"+ilPath;
    }
    @RequestMapping("/websocket/{ilPath}")
    public String websocket(@PathVariable("ilPath") String ilPath) throws Exception{
        return "/websocket/"+ilPath;
    }
    @ResponseBody
    @RequestMapping("/test")
    public JSONObject test(HttpServletRequest request){
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            System.out.println(names.nextElement());
        }
        JSONObject result = new JSONObject();
        result.put("success", "1");
        return result;
    }
}
