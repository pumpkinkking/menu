package com.menu.menu.service;

import com.menu.menu.entity.User;
import com.menu.menu.exception.BusinessException;
import com.menu.menu.mapper.UserMapper;
import com.menu.menu.service.FileUploadService;
import com.menu.menu.service.strategy.LoginStrategy;
import com.menu.menu.service.strategy.LoginStrategyFactory;
import com.menu.menu.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean updateAvatar(Long userId, String avatarPath) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        user.setAvatar(avatarPath);
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(user) > 0;
    }

    private final LoginStrategyFactory loginStrategyFactory;
    private final UserMapper userMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public UserServiceImpl(LoginStrategyFactory loginStrategyFactory, UserMapper userMapper, StringRedisTemplate stringRedisTemplate) {
        this.loginStrategyFactory = loginStrategyFactory;
        this.userMapper = userMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public LoginVO wechatLogin(String code) {
        LoginStrategy strategy = loginStrategyFactory.getStrategy("wechat");
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        return strategy.login(params);
    }

    @Override
    public boolean updateUsername(Long userId, String username) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        user.setUsername(username);
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(user) > 0;
    }

    @Override
    public LoginVO phoneLogin(String encryptedData, String iv, Long userId) {
        LoginStrategy strategy = loginStrategyFactory.getStrategy("phone");
        Map<String, Object> params = new HashMap<>();
        params.put("encryptedData", encryptedData);
        params.put("iv", iv);
        params.put("userId", userId);
        return strategy.login(params);
    }

        @Override
    public void getUserById(Long userId) {
        userMapper.selectById(userId);
    }
}