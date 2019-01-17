package com.banyechan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banyechan.model.UserModel;
import com.banyechan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    String REGEXP_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[\\S]{6,25}$";

    @Autowired
    private UserService userService;
    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        UserModel user = new UserModel();
        user.setUserName("shouji");
        Integer userCount = userService.getCountByUser(user);
        if(userCount == 0) {
            System.out.println("count ======= 0");
        }else {
            System.out.println("count ！= 0");
        }
        mav.setViewName("/user/test");
        return mav;
    }


    @RequestMapping("/list")
    public ModelAndView userList(HttpServletRequest request, HttpServletResponse response,UserModel userModel,Integer pageNo) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("menuId", "2");



        List<UserModel> userList = userService.listByUser(userModel);

        Integer recordTotal = userService.getCountByUser(userModel);


        mav.addObject("userList", userList);
        mav.addObject("userModel", userModel);
        mav.setViewName("/user/userList");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("------		 跳登录页面		-------");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/login");
        return mav;
    }

    //注册
    @RequestMapping("/loginIn")
    public @ResponseBody Map<String, Object> loginIn(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = true;
        String userName = httpRequest.getParameter("userName");
        String password = httpRequest.getParameter("password");

        Pattern pattern = Pattern.compile(REGEXP_PASSWORD);
        Matcher m = pattern.matcher(password);
        if(userName == null || userName.equals("")){
            map.put("message", "请输入姓名!");
            flag = false;
	   /*} else if (!m.matches()) {
		  map.put("message", "密码不符合规则！");
		  flag = false;*/
        }else if (password == null ||"".equals(password)) {
            map.put("message", "请输入密码！");
            flag = false;
        } else {
            UserModel user = new UserModel();
            user.setPassword(password);
            user.setUserName(userName);
            UserModel temUser = userService.getByUser(user);
            if(temUser != null){
                Integer state = temUser.getState();
                if(state == 2) {
                    flag = false;
                    map.put("message", "您的账号已被冻结,请联系管理员!");
                }else {
                    httpRequest.getSession().setAttribute("user", temUser);
                    flag = true;
                    map.put("message", "登录成功!");
                }
            }else{
                flag = false;
                map.put("message", "姓名或密码不正确,请重新输入!");
            }
        }
        map.put("success", flag);
        return map;
    }


    @RequestMapping("/loginOut")
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        logger.info(user.getUserName() + "------	退出登录	-------");
        session.removeAttribute("user");

        mav.setViewName("/user/login");
        return mav;
    }



    @RequestMapping("/save")
    public @ResponseBody Map<String, Object> saveUser(HttpServletRequest httpRequest, HttpServletResponse httpResponse,UserModel user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = 0;
        if(user != null) {
            Integer userId = user.getUserId();
            if(userId != null && userId > 0) {
                result = userService.editUser(user);
            }else {
                result = userService.addUser(user);
            }
        }

        map.put("success", result>0);
        if(result>0) {
            map.put("message", "保存成功");
        }else {
            map.put("message", "保存失败！！！");
        }

        return map;
    }

    @RequestMapping("/editLock")
    public @ResponseBody Map<String, Object> editLock(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Integer userId,Integer state) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = 0;
        if(userId != null && state != null) {
            UserModel user = new UserModel();
            user.setUserId(userId);
            if(state ==1) {
                user.setState(2);
            }else {
                user.setState(1);
            }
            result = userService.editUser(user);
        }

        map.put("success", result>0);
        if(result>0) {
            map.put("message", "修改成功");
        }else {
            map.put("message", "修改失败！！！");
        }

        return map;
    }


    @RequestMapping("/getUser")
    public @ResponseBody Map<String, Object> getUser(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Integer userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(userId != null ) {
            UserModel user = userService.selectByUserId(userId);
            if(user != null) {
                map.put("success", true);
                map.put("user", user);
            }else {
                map.put("success", true);
                map.put("message", "查无此人！");
            }
        }

        return map;
    }


    @RequestMapping("/vueList")
    public @ResponseBody Map<String, Object> vueList(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Map<String, Object> map = new HashMap<String, Object>();
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        List<UserModel> userList = userService.listAllUser();

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(7);


        map.put("success", true);
        map.put("intList", intList);
        map.put("userList", userList);
        return map;
    }

    @RequestMapping("/vueUserInfo")
    public @ResponseBody Map<String, Object> vueUserInfo(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Integer userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        if(userId != null ) {
            UserModel user = userService.selectByUserId(userId);
            if(user != null) {
                map.put("success", true);
                map.put("user", user);
            }else {
                map.put("success", true);
                map.put("message", "查无此人！");
            }
        }

        return map;
    }
}

