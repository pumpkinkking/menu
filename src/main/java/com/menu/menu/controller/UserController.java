package com.menu.menu.controller;

import com.menu.menu.service.FileUploadService;
import com.menu.menu.service.UserService;
import com.menu.menu.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 微信小程序登录
     */
    @PostMapping("/wechatLogin")
    public LoginVO wechatLogin(@RequestParam String code) {
        return userService.wechatLogin(code);
    }

    /**
     * 手机号登录
     */
    @PostMapping("/phoneLogin")
    public LoginVO phoneLogin(
            @RequestParam String encryptedData,
            @RequestParam String iv,
            @RequestParam Long userId) {
        return userService.phoneLogin(encryptedData, iv, userId);
    }

    /**
     * 更新用户名称
     */
    @PostMapping("/updateUsername")
    public boolean updateUsername(
            @RequestParam Long userId,
            @RequestParam String username) {
        return userService.updateUsername(userId, username);
    }

    /**
     * 初始化头像上传
     */
    @PostMapping("/avatar/initialize")
    public String initializeAvatarUpload(
            @RequestParam Long userId,
            @RequestParam String fileName) {
        String uploadId = fileUploadService.initializeUpload(userId, fileName);
        // 存储uploadId与userId的关联，有效期24小时
        stringRedisTemplate.opsForValue().set("upload:avatar:" + uploadId, userId.toString(), 24, TimeUnit.HOURS);
        return uploadId;
    }

    /**
     * 上传头像分片
     */
    @PostMapping("/avatar/chunk")
    public void uploadAvatarChunk(
            @RequestParam String uploadId,
            @RequestParam int chunkNumber,
            @RequestParam MultipartFile chunkFile) {
        fileUploadService.uploadChunk(uploadId, chunkNumber, chunkFile);
    }

    /**
     * 完成头像上传
     */
    @PostMapping("/avatar/complete")
    public String completeAvatarUpload(
            @RequestParam String uploadId) {
        String avatarUrl = fileUploadService.completeUpload(uploadId);
        // 获取userId并更新头像
        String userIdStr = stringRedisTemplate.opsForValue().get("upload:avatar:" + uploadId);
        if (userIdStr == null) {
            throw new com.menu.menu.exception.BusinessException("上传会话已过期");
        }
        Long userId = Long.valueOf(userIdStr);
        userService.updateAvatar(userId, avatarUrl);
        // 删除Redis中的关联
        stringRedisTemplate.delete("upload:avatar:" + uploadId);
        return avatarUrl;
    }
}