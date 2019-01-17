package com.banyechan.service;

import com.banyechan.model.UserModel;
import java.util.List;

public interface UserService {

    UserModel selectByUserId(Integer userId);

    Integer addUser(UserModel user);

    Integer editUser(UserModel user);

    UserModel getByUser(UserModel user);

    List<UserModel> listAllUser();

    List<UserModel> listByUser(UserModel user);

    Integer getCountByUser(UserModel user);

}
