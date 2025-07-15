SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 用户表 (sys_user)

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    user_id VARCHAR(32) PRIMARY KEY COMMENT '用户ID',
    open_id VARCHAR(255) DEFAULT NULL COMMENT '微信openid',
    phone_num VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    username VARCHAR(50) DEFAULT NULL COMMENT '用户名',
    avatar_url VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '用户信息表';

-- 食材表 (ingredient)
DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient (
    ingredient_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '食材ID',
    user_id VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    name VARCHAR(100) DEFAULT NULL COMMENT '食材名称',
    quantity INT(11) DEFAULT NULL COMMENT '数量',
    unit VARCHAR(20) DEFAULT NULL COMMENT '单位',
    location VARCHAR(50) DEFAULT NULL COMMENT '存放位置',
    freshness VARCHAR(20) DEFAULT NULL COMMENT '新鲜度',
    product_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生产时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id)
) COMMENT '食材信息表';

-- 菜篮子表 (basket)
DROP TABLE IF EXISTS basket;
CREATE TABLE basket (
    basket_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '菜篮子ID',
    user_id VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    ingredient_id VARCHAR(32) DEFAULT NULL COMMENT '食材ID',
    menu_id VARCHAR(32) DEFAULT NULL COMMENT '菜品ID',
    quantity INT(11) DEFAULT NULL COMMENT '数量',
    unit VARCHAR(20) DEFAULT NULL COMMENT '单位',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_ingredient_id (ingredient_id)
) COMMENT '菜篮子表';

-- 用户餐单表 (user_menu)
DROP TABLE IF EXISTS user_menu;
CREATE TABLE user_menu (
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    menu_id VARCHAR(32) NOT NULL COMMENT '餐单ID',
    sort_order INT(11) DEFAULT 0 COMMENT '排序序号',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_deleted TINYINT(4) DEFAULT 0 COMMENT '删除标志(0-未删除,1-已删除)',
    PRIMARY KEY (user_id, menu_id)
) COMMENT '用户餐单表，记录用户下有哪些菜品';

-- 餐单表 (menu)
DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
    menu_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '餐单ID',
    menu_name VARCHAR(100) DEFAULT NULL COMMENT '餐单名称',
    description TEXT DEFAULT NULL COMMENT '餐单描述',
    cover_image_url VARCHAR(255) DEFAULT NULL COMMENT '封面图片URL',
    thumbnail_url VARCHAR(255) DEFAULT NULL COMMENT '封面缩略图URL',
    category_id VARCHAR(32) DEFAULT NULL COMMENT '分类ID',
    user_id VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    view_count INT(11) DEFAULT 0 COMMENT '查看次数',
    collect_count INT(11) DEFAULT 0 COMMENT '收藏次数',
    share_count INT(11) DEFAULT 0 COMMENT '分享次数',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_deleted TINYINT(4) DEFAULT 0 COMMENT '删除标志(0-未删除,1-已删除)',
    INDEX idx_user_id (user_id),
    INDEX idx_category_id (category_id)
) COMMENT '餐单表';

-- 餐单食材表 (menu_ingredient)
DROP TABLE IF EXISTS menu_ingredient;
CREATE TABLE menu_ingredient (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '食材ID',
    menu_id VARCHAR(32) DEFAULT NULL COMMENT '餐单ID',
    ingredient_id VARCHAR(32) DEFAULT NULL COMMENT '食材ID',
    ingredient_name VARCHAR(100) DEFAULT NULL COMMENT '食材名称',
    quantity VARCHAR(20) DEFAULT NULL COMMENT '食材数量',
    unit VARCHAR(20) DEFAULT NULL COMMENT '计量单位',
    sort_order INT(11) DEFAULT 0 COMMENT '排序序号',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_menu_id (menu_id)
) COMMENT '餐单食材表';

-- 餐单步骤表 (menu_step)
DROP TABLE IF EXISTS menu_step;
CREATE TABLE menu_step (
    menu_step_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '步骤ID',
    menu_id VARCHAR(32) DEFAULT NULL COMMENT '餐单ID',
    content TEXT DEFAULT NULL COMMENT '步骤内容',
    image_url VARCHAR(255) DEFAULT NULL COMMENT '步骤图片URL',
    sort_order INT(11) DEFAULT 0 COMMENT '排序序号',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_menu_id (menu_id)
) COMMENT '餐单步骤表';

-- 餐单收藏表 (menu_collection)
DROP TABLE IF EXISTS menu_collection;
CREATE TABLE menu_collection (
    menu_collection_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    user_id VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    menu_id VARCHAR(32) DEFAULT NULL COMMENT '餐单ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_menu_id (menu_id),
    UNIQUE KEY uk_user_menu (user_id, menu_id)
) COMMENT '餐单收藏表';

-- 餐单分享表 (menu_share)
DROP TABLE IF EXISTS menu_share;
CREATE TABLE menu_share (
    menu_share_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '分享记录ID',
    menu_id VARCHAR(32) DEFAULT NULL COMMENT '餐单ID',
    user_id VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    share_channel VARCHAR(50) DEFAULT NULL COMMENT '分享渠道',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_menu_id (menu_id)
) COMMENT '餐单分享表';

-- 订单表 (order)
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    order_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    order_no VARCHAR(50) DEFAULT NULL COMMENT '订单编号',
    user_id VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    order_time DATETIME DEFAULT NULL COMMENT '下单时间',
    finish_time DATETIME DEFAULT NULL COMMENT '完成时间',
    status VARCHAR(20) DEFAULT NULL COMMENT '状态',
    total_price DECIMAL(10,2) DEFAULT NULL COMMENT '总价',
    notes VARCHAR(500) DEFAULT NULL COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id),
    INDEX idx_order_no (order_no)
) COMMENT '订单表';

-- 订单明细表 (order_detail)
DROP TABLE IF EXISTS order_detail;
CREATE TABLE order_detail (
    order_detail_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    order_no VARCHAR(50) DEFAULT NULL COMMENT '订单编号',
    menu_id VARCHAR(32) DEFAULT NULL COMMENT '菜品ID',
    menu_name VARCHAR(100) DEFAULT NULL COMMENT '菜品名称',
    menu_price DECIMAL(10,2) DEFAULT NULL COMMENT '菜品单价',
    quantity VARCHAR(20) DEFAULT NULL COMMENT '数量',
    subtotal DECIMAL(10,2) DEFAULT NULL COMMENT '小计金额（单价 × 数量）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_order_no (order_no)
) COMMENT '订单明细表';

-- 计划表 (plan)
DROP TABLE IF EXISTS plan;
CREATE TABLE plan (
    plan_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '计划ID',
    plan_date DATE NOT NULL COMMENT '计划日期',
    meals JSON NOT NULL COMMENT '餐食列表',
    user_id VARCHAR(32) NOT NULL COMMENT '用户id',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_id (user_id)
) COMMENT '计划表';

SET FOREIGN_KEY_CHECKS = 1;