package com.menu.menu.service;

import com.menu.menu.vo.LoginVO;

public interface UserService {
    /**
     * 微信小程序登录
     * @param code 登录凭证
     * @return 登录结果，包含token和用户ID
     */
    LoginVO wechatLogin(String code);

    /**
     * 手机号登录
     * @param encryptedData 加密的手机号信息
     * @param iv 加密向量
     * @param userId 用户ID
     * @return 登录结果，包含token和用户ID
     */
    LoginVO phoneLogin(String encryptedData, String iv, Long userId);

    /**
     * 更新用户名称
     * @param userId 用户ID
     * @param username 新用户名
     * @return 是否更新成功
     */
    boolean updateUsername(Long userId, String username);

    /**
     * 上传用户头像
     * @param userId 用户ID
     * @param file 头像文件
     * @return 头像URL
     */
    /**
     * 更新用户头像URL
     * @param userId 用户ID
     * @param avatarUrl 头像URL
     * @return 是否更新成功
     */
    boolean updateAvatar(Long userId, String avatarUrl);

    void getUserById(Long id);
}