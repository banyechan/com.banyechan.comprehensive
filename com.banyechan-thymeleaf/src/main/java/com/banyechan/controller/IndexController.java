package com.banyechan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/index")
public class IndexController {

    AtomicInteger num = new AtomicInteger(0);

    @RequestMapping("/home")
    public String home() {
        StringBuilder sb = new StringBuilder("--- 第{" + num + "}次访问 ---");
        ;
        int nextNum = num.incrementAndGet();
        return sb.toString();
    }



    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("index");
        return  mav;
    }


}
