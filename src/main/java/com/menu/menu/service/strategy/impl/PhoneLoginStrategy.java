package com.menu.menu.service.strategy.impl;

import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.menu.entity.User;
import com.menu.menu.mapper.UserMapper;
import com.menu.menu.service.strategy.LoginStrategy;
import com.menu.menu.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

  @Component
  @Slf4j
  public class PhoneLoginStrategy implements LoginStrategy { @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginVO login(Map<String, Object> params) {
        try {
            String code = (String) params.get("code");
            String encryptedData = (String) params.get("encryptedData");
            String iv = (String) params.get("iv");
            
            // 使用code获取会话信息
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
            String openid = sessionResult.getOpenid();
            
            // 根据openid查询用户
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("openid", openid));
            if (user == null) {
                throw new RuntimeException("用户不存在");
            }

            WxMaPhoneNumberInfo phoneInfo = wxMaService.getUserService().getPhoneNoInfo(user.getSessionKey(), encryptedData, iv);
            String phone = phoneInfo.getPhoneNumber();
            user.setPhone(phone);
            userMapper.updateById(user);

            String token = UUID.randomUUID().toString();
            LoginVO loginVO = new LoginVO();
            loginVO.setToken(token);
            loginVO.setUserId(user.getId());
            return loginVO;
        } catch (Exception e) {
            throw new RuntimeException("手机号登录失败: " + e.getMessage());
        }
    }

    @Override
    public String getLoginType() {
        return "phone";
    }
}