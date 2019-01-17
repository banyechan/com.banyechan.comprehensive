package com.banyechan.banyechan_controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("index");
        return  mav;
    }

}
