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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.menu.menu.config.JwtTokenProvider;

@Component
public class WechatLoginStrategy implements LoginStrategy {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginVO login(Map<String, Object> params) {
        String code = (String) params.get("code");
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            String openid = session.getOpenid();

            User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openid));
            if (user == null) {
                user = new User();
                user.setOpenId(openid);
                user.setUserId(generateUserId(session.getOpenid()));
                userMapper.insert(user);
            }

            // 使用JWT生成token，有效期24小时
            String token = jwtTokenProvider.generateToken(user.getUserId());
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

    /**
     * 使用MD5加密算法生成唯一user_id
     * @param input 输入字符串(OpenID或UnionID)
     * @return MD5加密后的32位字符串
     */
    private String generateUserId(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法获取失败", e);
        }
    }
}