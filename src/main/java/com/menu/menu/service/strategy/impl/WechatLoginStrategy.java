package com.menu.menu.service.strategy.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.menu.menu.entity.User;
import com.menu.menu.mapper.UserMapper;
import com.menu.menu.service.strategy.LoginStrategy;
import com.menu.menu.vo.LoginVO;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.UUID;

@Component
public class WechatLoginStrategy implements LoginStrategy {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginVO login(Map<String, Object> params) {
        String code = (String) params.get("code");
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            String openid = session.getOpenid();

            User user = userMapper.selectOne(new QueryWrapper<User>().eq("openid", openid));
            if (user == null) {
                user = new User();
                user.setOpenId(openid);
                userMapper.insert(user);
            }

            String token = UUID.randomUUID().toString();
            LoginVO loginVO = new LoginVO();
            loginVO.setToken(token);
            loginVO.setUserId(user.getUserId());
            return loginVO;
        } catch (WxErrorException e) {
            throw new RuntimeException("微信登录失败: " + e.getMessage());
        }
    }

    @Override
    public String getLoginType() {
        return "wechat";
    }
}