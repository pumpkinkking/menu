package com.menu.menu.service.impl;

import com.menu.menu.entity.User;
import com.menu.menu.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService实现类，用于加载用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名加载用户信息
     * @param username 用户名
     * @return UserDetails 用户详情
     * @throws UsernameNotFoundException 用户名未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在: " + username);
        }


        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .build();
    }
}