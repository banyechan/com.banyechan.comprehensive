package com.banyechan.mapper;

import com.banyechan.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserModelMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    UserModel selectByMap(Map<String,Object> map);

    List<UserModel> listByMap(Map<String, Object> map);

    Integer getCountByMap(Map<String, Object> map);
}
