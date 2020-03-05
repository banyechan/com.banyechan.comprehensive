package com.banyechan.comprehensive.banyechan_tomcat_war.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
