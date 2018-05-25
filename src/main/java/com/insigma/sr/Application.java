package com.insigma.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@EnableScheduling
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages={
        "com.insigma.sr.tasks",
        "com.insigma.sr.controller"
})
@SpringBootApplication
public class Application{
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("hello");
        return "hellosss";
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
