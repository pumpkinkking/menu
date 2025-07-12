package com.menu.menu.service.strategy;

import com.menu.menu.vo.LoginVO;
import java.util.Map;

public interface LoginStrategy {
    /**
     * 执行登录逻辑
     * @param params 登录参数
     * @return 登录结果
     */
    LoginVO login(Map<String, Object> params);

    /**
     * 获取登录类型
     * @return 登录类型标识
     */
    String getLoginType();
}