package com.menu.menu.service;

import com.menu.menu.entity.User;
import com.menu.menu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 缓存预热服务，系统启动时加载热点数据到Redis
 */
@Service
public class CacheWarmupService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 系统启动后执行缓存预热
     */
    @PostConstruct
    public void warmupCache() {
        System.out.println("开始执行缓存预热...");
        long startTime = System.currentTimeMillis();

        // 1. 预热热门用户数据 (示例：加载前100名活跃用户)
        warmupHotUsers();

        long endTime = System.currentTimeMillis();
        System.out.println("缓存预热完成，耗时: " + (endTime - startTime) + "ms");
    }

    /**
     * 预热热门用户数据
     */
    private void warmupHotUsers() {
        try {
            // 查询热门用户列表 (实际业务中可根据活跃度、等级等条件筛选)
            List<User> hotUsers = userMapper.selectHotUsers(100); // 需要在UserMapper中添加该方法
            for (User user : hotUsers) {
                // 调用带缓存注解的方法，将数据加载到缓存
                userService.getUserById(user.getId());
            }
            System.out.println("预热热门用户数据完成，共加载: " + hotUsers.size() + "条记录");
        } catch (Exception e) {
            System.err.println("预热热门用户数据失败: " + e.getMessage());
            // 缓存预热失败不影响系统启动
        }
    }

    /**
     * 手动刷新缓存的方法
     */
    @CacheEvict(value = "userCache", allEntries = true)
    public void refreshUserCache() {
        System.out.println("手动刷新用户缓存");
    }
}