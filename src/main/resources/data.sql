-- 测试数据脚本
-- 基于schema.sql生成，每张表约10条记录

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 用户表测试数据
-- ----------------------------
INSERT INTO `user` (`openid`, `phone`, `session_key`, `username`, `avatar`) VALUES
('o6_bmjrPTlm6_2sgVt7hMZOPfL2M', '13800138000', 'skey123456', '张三', 'https://example.com/avatar/1.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2N', '13900139000', 'skey234567', '李四', 'https://example.com/avatar/2.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2O', '13700137000', 'skey345678', '王五', 'https://example.com/avatar/3.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2P', '13600136000', 'skey456789', '赵六', 'https://example.com/avatar/4.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2Q', '13500135000', 'skey567890', '钱七', 'https://example.com/avatar/5.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2R', '13400134000', 'skey678901', '孙八', 'https://example.com/avatar/6.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2S', '13300133000', 'skey789012', '周九', 'https://example.com/avatar/7.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2T', '13200132000', 'skey890123', '吴十', 'https://example.com/avatar/8.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2U', '13100131000', 'skey901234', '郑十一', 'https://example.com/avatar/9.jpg'),
('o6_bmjrPTlm6_2sgVt7hMZOPfL2V', '13000130000', 'skey012345', '王十二', 'https://example.com/avatar/10.jpg');

-- ----------------------------
-- 食材表测试数据
-- ----------------------------
INSERT INTO `ingredient` (`user_id`, `name`, `quantity`, `unit`, `location`, `freshness`) VALUES
(1, '西红柿', 5, '个', '冰箱', '新鲜'),
(1, '鸡蛋', 10, '个', '冰箱', '新鲜'),
(1, '猪肉', 500, '克', '冰箱', '新鲜'),
(2, '土豆', 3, '个', '阳台', '较新鲜'),
(2, '牛肉', 300, '克', '冰箱', '新鲜'),
(3, '青椒', 4, '个', '冰箱', '新鲜'),
(3, '大米', 2000, '克', '橱柜', '干燥'),
(4, '面条', 1000, '克', '橱柜', '干燥'),
(5, '黄瓜', 3, '根', '冰箱', '新鲜'),
(6, '白菜', 1, '棵', '阳台', '较新鲜');

-- ----------------------------
-- 购物车表测试数据
-- ----------------------------
INSERT INTO `basket` (`user_id`, `ingredient_id`, `quantity`, `unit`) VALUES
(1, 1, 2, '个'),
(1, 2, 4, '个'),
(2, 4, 2, '个'),
(3, 6, 3, '个'),
(3, 7, 500, '克'),
(4, 8, 500, '克'),
(5, 9, 2, '根'),
(6, 10, 1, '棵'),
(7, 3, 200, '克'),
(8, 5, 200, '克');

-- ----------------------------
-- 餐单表测试数据
-- ----------------------------
INSERT INTO `menu` (`name`, `description`, `cover_image_url`, `thumbnail_url`, `category_id`, `user_id`, `view_count`, `collect_count`, `share_count`) VALUES
('西红柿炒鸡蛋', '经典家常菜，营养丰富', 'https://example.com/menu/1.jpg', 'https://example.com/menu/thumb/1.jpg', 1, 1, 120, 35, 12),
('红烧肉', '传统名菜，肥而不腻', 'https://example.com/menu/2.jpg', 'https://example.com/menu/thumb/2.jpg', 2, 1, 230, 89, 45),
('土豆炖牛肉', '营养美味的家常菜', 'https://example.com/menu/3.jpg', 'https://example.com/menu/thumb/3.jpg', 2, 2, 180, 56, 23),
('青椒肉丝', '下饭神器', 'https://example.com/menu/4.jpg', 'https://example.com/menu/thumb/4.jpg', 1, 3, 156, 42, 18),
('阳春面', '简单美味的主食', 'https://example.com/menu/5.jpg', 'https://example.com/menu/thumb/5.jpg', 3, 4, 98, 27, 9),
('拍黄瓜', '清爽开胃的凉菜', 'https://example.com/menu/6.jpg', 'https://example.com/menu/thumb/6.jpg', 4, 5, 76, 15, 6),
('醋溜白菜', '酸甜可口的家常菜', 'https://example.com/menu/7.jpg', 'https://example.com/menu/thumb/7.jpg', 1, 6, 110, 31, 14),
('鱼香肉丝', '川菜经典', 'https://example.com/menu/8.jpg', 'https://example.com/menu/thumb/8.jpg', 2, 7, 205, 78, 36),
('宫保鸡丁', '香辣可口', 'https://example.com/menu/9.jpg', 'https://example.com/menu/thumb/9.jpg', 2, 8, 178, 63, 29),
('麻婆豆腐', '川菜代表', 'https://example.com/menu/10.jpg', 'https://example.com/menu/thumb/10.jpg', 1, 9, 143, 47, 21);

-- ----------------------------
-- 餐单食材表测试数据
-- ----------------------------
INSERT INTO `menu_ingredient` (`menu_id`, `name`, `quantity`, `unit`, `sort_order`) VALUES
(1, '西红柿', '2', '个', 1),
(1, '鸡蛋', '4', '个', 2),
(1, '葱', '1', '根', 3),
(2, '五花肉', '500', '克', 1),
(2, '姜', '3', '片', 2),
(3, '土豆', '2', '个', 1),
(3, '牛肉', '300', '克', 2),
(4, '青椒', '3', '个', 1),
(4, '猪肉', '200', '克', 2),
(5, '面条', '100', '克', 1);

-- ----------------------------
-- 餐单步骤表测试数据
-- ----------------------------
INSERT INTO `menu_step` (`menu_id`, `content`, `image_url`, `sort_order`) VALUES
(1, '西红柿切块', 'https://example.com/step/1-1.jpg', 1),
(1, '鸡蛋打散', 'https://example.com/step/1-2.jpg', 2),
(1, '热油炒鸡蛋', 'https://example.com/step/1-3.jpg', 3),
(2, '五花肉切块焯水', 'https://example.com/step/2-1.jpg', 1),
(2, '炒糖色', 'https://example.com/step/2-2.jpg', 2),
(3, '土豆切块', 'https://example.com/step/3-1.jpg', 1),
(3, '牛肉焯水', 'https://example.com/step/3-2.jpg', 2),
(4, '青椒切丝', 'https://example.com/step/4-1.jpg', 1),
(4, '猪肉切丝腌制', 'https://example.com/step/4-2.jpg', 2),
(5, '煮面条', 'https://example.com/step/5-1.jpg', 1);

-- ----------------------------
-- 餐单收藏表测试数据
-- ----------------------------
INSERT INTO `menu_collection` (`user_id`, `menu_id`) VALUES
(2, 1),
(3, 1),
(4, 2),
(5, 3),
(6, 4),
(7, 5),
(8, 6),
(9, 7),
(10, 8),
(1, 9);

-- ----------------------------
-- 餐单分享表测试数据
-- ----------------------------
INSERT INTO `menu_share` (`menu_id`, `user_id`, `share_channel`) VALUES
(1, 1, 'WECHAT'),
(2, 1, 'WEIBO'),
(3, 2, 'WECHAT'),
(4, 3, 'QQ'),
(5, 4, 'WECHAT'),
(6, 5, 'WEIBO'),
(7, 6, 'WECHAT'),
(8, 7, 'QQ'),
(9, 8, 'WECHAT'),
(10, 9, 'WEIBO');

-- ----------------------------
-- 订单表测试数据
-- ----------------------------
INSERT INTO `order` (`order_no`, `user_id`, `date`, `status`, `total_price`) VALUES
('ORD20231001001', 1, '2023-10-01', 'PAID', 88.00),
('ORD20231001002', 2, '2023-10-01', 'PAID', 128.00),
('ORD20231001003', 3, '2023-10-01', 'PAID', 68.00),
('ORD20231001004', 4, '2023-10-01', 'PAID', 99.00),
('ORD20231001005', 5, '2023-10-01', 'PAID', 158.00),
('ORD20231001006', 6, '2023-10-01', 'PAID', 78.00),
('ORD20231001007', 7, '2023-10-01', 'PAID', 108.00),
('ORD20231001008', 8, '2023-10-01', 'PAID', 88.00),
('ORD20231001009', 9, '2023-10-01', 'PAID', 138.00),
('ORD20231001010', 10, '2023-10-01', 'PAID', 66.00);

-- ----------------------------
-- 计划表测试数据
-- ----------------------------
INSERT INTO `plan` (`title`, `date`, `meals`) VALUES
('周一 meal plan', '2023-10-09', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[1,4]},{"mealType":"DINNER","menuIds":[2]}]'),
('周二 meal plan', '2023-10-10', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[3,4]},{"mealType":"DINNER","menuIds":[7]}]'),
('周三 meal plan', '2023-10-11', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[1,6]},{"mealType":"DINNER","menuIds":[8]}]'),
('周四 meal plan', '2023-10-12', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[4,6]},{"mealType":"DINNER","menuIds":[9]}]'),
('周五 meal plan', '2023-10-13', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[1,3]},{"mealType":"DINNER","menuIds":[2]}]'),
('周六 meal plan', '2023-10-14', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[4,6]},{"mealType":"DINNER","menuIds":[7]}]'),
('周日 meal plan', '2023-10-15', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[1,3]},{"mealType":"DINNER","menuIds":[8]}]'),
('下周一 meal plan', '2023-10-16', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[4,6]},{"mealType":"DINNER","menuIds":[9]}]'),
('下周二 meal plan', '2023-10-17', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[1,3]},{"mealType":"DINNER","menuIds":[2]}]'),
('下周三 meal plan', '2023-10-18', '[{"mealType":"BREAKFAST","menuIds":[5]},{"mealType":"LUNCH","menuIds":[4,6]},{"mealType":"DINNER","menuIds":[7]}]');

SET FOREIGN_KEY_CHECKS = 1;