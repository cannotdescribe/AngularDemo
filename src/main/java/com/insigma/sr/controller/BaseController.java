package main.java.com.insigma.sr.controller;

import com.insigma.sr.utils.CatUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;

@Controller
public class BaseController {

    @ResponseBody
    @RequestMapping("/add")
    public void add(@RequestParam(value = "ilPath", required = false) MultipartFile file, HttpServletRequest request) throws Exception{

        String webapp = CatUtils.getWebStaticResource();
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
