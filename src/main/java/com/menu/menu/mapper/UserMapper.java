package com.menu.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.menu.menu.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectHotUsers(int i);

    User findByUsername(String username);
}