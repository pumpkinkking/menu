package com.menu.menu.util;

/**
 * 用户上下文持有类，用于在ThreadLocal中存储当前用户信息
 */
public class UserContextHolder {
    private static final ThreadLocal<String> USER_ID_CONTEXT = new ThreadLocal<>();

    /**
     * 设置当前用户ID
     * @param userId 用户ID
     */
    public static void setUserId(String userId) {
        USER_ID_CONTEXT.set(userId);
    }

    /**
     * 获取当前用户ID
     * @return 用户ID，未登录时返回null
     */
    public static String getUserId() {
        return USER_ID_CONTEXT.get();
    }

    /**
     * 清除当前线程的用户ID，防止内存泄漏
     */
    public static void clear() {
        USER_ID_CONTEXT.remove();
    }
}