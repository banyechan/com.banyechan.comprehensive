package com.banyechan.serviceImpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banyechan.mapper.UserModelMapper;
import com.banyechan.model.UserModel;
import com.banyechan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userMapper;

    @Override
    public UserModel selectByUserId(Integer userId){
        UserModel user = userMapper.selectByPrimaryKey(userId);

        return user;
    }

    @Override
    public Integer addUser(UserModel user){
        Integer result = userMapper.insertSelective(user);

        return result;
    }

    @Override
    public Integer editUser(UserModel user){
        Integer result = userMapper.updateByPrimaryKeySelective(user);

        return result;
    }


    @Override
    public UserModel getByUser(UserModel user){
        Map<String,Object> map = new HashMap<String,Object>();

        if (user != null && user.getUserName() != null && !user.getUserName().equals("")){
            map.put("userName",user.getUserName());
        }
        if(user != null && user.getPassword() != null && !user.getPassword().equals("")){
            map.put("password",user.getPassword());
        }

        UserModel result = userMapper.selectByMap(map);

        return result;
    }

    @Override
    public List<UserModel> listAllUser() {
        Map<String, Object> map = new HashMap<String,Object>();

        return userMapper.listByMap(map);
    }

    @Override
    public List<UserModel> listByUser(UserModel user) {
        Map<String, Object> map = new HashMap<String,Object>();
        if(user != null) {
            if(user.getUserName() != null && !"".equals(user.getUserName())) {
                map.put("userName", user.getUserName());
            }
            if(user.getRole() != null) {
                map.put("role", user.getRole());
            }
            if(user.getState() != null) {
                map.put("state", user.getState());
            }

        }


        return userMapper.listByMap(map);
    }

    @Override
    public Integer getCountByUser(UserModel user) {
        Map<String, Object> map = new HashMap<String,Object>();
        if(user != null) {
            if(user.getUserName() != null && !"".equals(user.getUserName())) {
                map.put("userName", user.getUserName());
            }
            if(user.getRole() != null) {
                map.put("role", user.getRole());
            }
            if(user.getState() != null) {
                map.put("state", user.getState());
            }
        }

        return userMapper.getCountByMap(map);
    }



}

