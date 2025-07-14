-- 数据库表结构脚本
-- 基于entity自动生成

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `openid` varchar(255) DEFAULT NULL COMMENT '微信openid',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `session_key` varchar(255) DEFAULT NULL COMMENT '会话密钥',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- 食材表
-- ----------------------------
DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食材ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(100) DEFAULT NULL COMMENT '食材名称',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `location` varchar(50) DEFAULT NULL COMMENT '存放位置',
  `freshness` varchar(20) DEFAULT NULL COMMENT '新鲜度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食材表';

-- ----------------------------
-- 购物车表
-- ----------------------------
DROP TABLE IF EXISTS `basket`;
CREATE TABLE `basket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `ingredient_id` bigint(20) DEFAULT NULL COMMENT '食材ID',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_ingredient_id` (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- ----------------------------
-- 餐单表
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '餐单ID',
  `name` varchar(100) DEFAULT NULL COMMENT '餐单名称',
  `description` text COMMENT '餐单描述',
  `cover_image_url` varchar(255) DEFAULT NULL COMMENT '封面图片URL',
  `thumbnail_url` varchar(255) DEFAULT NULL COMMENT '封面缩略图URL',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `view_count` int(11) DEFAULT 0 COMMENT '查看次数',
  `collect_count` int(11) DEFAULT 0 COMMENT '收藏次数',
  `share_count` int(11) DEFAULT 0 COMMENT '分享次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) DEFAULT 0 COMMENT '删除标志(0-未删除,1-已删除)',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='餐单表';

-- ----------------------------
-- 餐单食材表
-- ----------------------------
DROP TABLE IF EXISTS `menu_ingredient`;
CREATE TABLE `menu_ingredient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食材ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '餐单ID',
  `name` varchar(100) DEFAULT NULL COMMENT '食材名称',
  `quantity` varchar(20) DEFAULT NULL COMMENT '食材数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '计量单位',
  `sort_order` int(11) DEFAULT 0 COMMENT '排序序号',
  PRIMARY KEY (`id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='餐单食材表';

-- ----------------------------
-- 餐单步骤表
-- ----------------------------
DROP TABLE IF EXISTS `menu_step`;
CREATE TABLE `menu_step` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '步骤ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '餐单ID',
  `content` text COMMENT '步骤内容',
  `image_url` varchar(255) DEFAULT NULL COMMENT '步骤图片URL',
  `sort_order` int(11) DEFAULT 0 COMMENT '排序序号',
  PRIMARY KEY (`id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='餐单步骤表';

-- ----------------------------
-- 餐单收藏表
-- ----------------------------
DROP TABLE IF EXISTS `menu_collection`;
CREATE TABLE `menu_collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '餐单ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_menu_id` (`menu_id`),
  UNIQUE KEY `uk_user_menu` (`user_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='餐单收藏表';

-- ----------------------------
-- 餐单分享表
-- ----------------------------
DROP TABLE IF EXISTS `menu_share`;
CREATE TABLE `menu_share` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分享记录ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '餐单ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `share_channel` varchar(50) DEFAULT NULL COMMENT '分享渠道',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '分享时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='餐单分享表';

-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `date` varchar(20) DEFAULT NULL COMMENT '日期',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- 计划表
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '计划ID',
  `title` varchar(255) NOT NULL COMMENT '计划标题',
  `date` date NOT NULL COMMENT '计划日期',
  `meals` json NOT NULL COMMENT '餐食列表',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='计划表';

SET FOREIGN_KEY_CHECKS = 1;