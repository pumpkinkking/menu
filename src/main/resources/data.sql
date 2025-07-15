-- 禁用外键检查，确保数据插入顺序不影响
SET FOREIGN_KEY_CHECKS = 0;

-- 用户表 (sys_user) - 生成10条随机数据
INSERT INTO sys_user (user_id, open_id, phone_num, username, avatar_url) VALUES
('u1001', CONCAT('open_', UUID()), CONCAT('138', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1002', CONCAT('open_', UUID()), CONCAT('139', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1003', CONCAT('open_', UUID()), CONCAT('137', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1004', CONCAT('open_', UUID()), CONCAT('136', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1005', CONCAT('open_', UUID()), CONCAT('135', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1006', CONCAT('open_', UUID()), CONCAT('134', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1007', CONCAT('open_', UUID()), CONCAT('133', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1008', CONCAT('open_', UUID()), CONCAT('132', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1009', CONCAT('open_', UUID()), CONCAT('131', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg')),
('u1010', CONCAT('open_', UUID()), CONCAT('130', FLOOR(RAND()*100000000)), CONCAT('用户', FLOOR(RAND()*1000)), CONCAT('https://avatar.com/', UUID(), '.jpg'));

-- 食材表 (ingredient) - 生成10条随机数据
INSERT INTO ingredient (user_id, name, quantity, unit, location, freshness, product_time) VALUES
('u1001', '西红柿', FLOOR(RAND()*10)+1, '个', '冰箱', '新鲜', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY)),
('u1002', '鸡蛋', FLOOR(RAND()*20)+1, '个', '冰箱', '新鲜', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY)),
('u1003', '猪肉', FLOOR(RAND()*2000)+500, '克', '冰箱', '较新鲜', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*15) DAY)),
('u1004', '白菜', FLOOR(RAND()*5)+1, '棵', '阳台', '新鲜', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*7) DAY)),
('u1005', '土豆', FLOOR(RAND()*10)+1, '个', '阳台', '新鲜', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY)),
('u1006', '青椒', FLOOR(RAND()*5)+1, '个', '冰箱', '新鲜', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*10) DAY)),
('u1007', '大米', FLOOR(RAND()*5)+1, '千克', '橱柜', '干燥', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*90) DAY)),
('u1008', '面条', FLOOR(RAND()*10)+1, '包', '橱柜', '干燥', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*60) DAY)),
('u1009', '酱油', FLOOR(RAND()*2)+1, '瓶', '橱柜', '正常', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*180) DAY)),
('u1010', '醋', FLOOR(RAND()*2)+1, '瓶', '橱柜', '正常', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*180) DAY));

-- 餐单表 (menu) - 生成10条随机数据
INSERT INTO menu (menu_name, description, cover_image_url, thumbnail_url, category_id, user_id, view_count, collect_count, share_count) VALUES
('西红柿炒鸡蛋', '经典家常菜，营养丰富', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1001', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('红烧肉', '传统名菜，肥而不腻', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1002', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('鱼香肉丝', '川菜经典，酸甜可口', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1003', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('宫保鸡丁', '川菜代表，香辣下饭', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1004', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('麻婆豆腐', '川菜名菜，麻辣鲜香', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1005', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('清蒸鱼', '粤菜经典，鲜嫩可口', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1006', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('北京烤鸭', '京菜代表，外酥里嫩', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1007', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('水煮鱼', '川菜经典，麻辣开胃', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1008', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('回锅肉', '川菜名菜，香气扑鼻', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1009', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200)),
('夫妻肺片', '川菜凉菜，麻辣鲜香', CONCAT('https://menu-img.com/', UUID(), '.jpg'), CONCAT('https://menu-img.com/thumb/', UUID(), '.jpg'), CONCAT('cat_', FLOOR(RAND()*10)+1), 'u1010', FLOOR(RAND()*1000), FLOOR(RAND()*500), FLOOR(RAND()*200));

-- 用户餐单表 (user_menu) - 生成10条随机数据
INSERT INTO user_menu (user_id, menu_id, sort_order) VALUES
('u1001', 1, FLOOR(RAND()*100)),
('u1001', 2, FLOOR(RAND()*100)),
('u1002', 3, FLOOR(RAND()*100)),
('u1002', 4, FLOOR(RAND()*100)),
('u1003', 5, FLOOR(RAND()*100)),
('u1003', 6, FLOOR(RAND()*100)),
('u1004', 7, FLOOR(RAND()*100)),
('u1004', 8, FLOOR(RAND()*100)),
('u1005', 9, FLOOR(RAND()*100)),
('u1005', 10, FLOOR(RAND()*100));

-- 菜篮子表 (basket) - 生成10条随机数据
INSERT INTO basket (user_id, ingredient_id, menu_id, quantity, unit) VALUES
('u1001', 1, 1, FLOOR(RAND()*5)+1, '个'),
('u1001', 2, 1, FLOOR(RAND()*10)+1, '个'),
('u1002', 3, 2, FLOOR(RAND()*500)+500, '克'),
('u1003', 4, 3, FLOOR(RAND()*2)+1, '棵'),
('u1004', 5, 4, FLOOR(RAND()*5)+1, '个'),
('u1005', 6, 5, FLOOR(RAND()*3)+1, '个'),
('u1006', 7, 6, FLOOR(RAND()*2)+1, '千克'),
('u1007', 8, 7, FLOOR(RAND()*3)+1, '包'),
('u1008', 9, 8, 1, '瓶'),
('u1009', 10, 9, 1, '瓶');

-- 餐单食材表 (menu_ingredient) - 生成10条随机数据
INSERT INTO menu_ingredient (menu_id, ingredient_id, ingredient_name, quantity, unit, sort_order) VALUES
(1, 1, '西红柿', '2', '个', 1),
(1, 2, '鸡蛋', '3', '个', 2),
(2, 3, '猪肉', '500', '克', 1),
(3, 4, '白菜', '1', '棵', 1),
(4, 5, '土豆', '2', '个', 1),
(5, 6, '青椒', '3', '个', 1),
(6, 7, '大米', '1', '千克', 1),
(7, 8, '面条', '1', '包', 1),
(8, 9, '酱油', '1', '勺', 1),
(9, 10, '醋', '1', '勺', 1);

-- 餐单步骤表 (menu_step) - 生成10条随机数据
INSERT INTO menu_step (menu_id, content, image_url, sort_order) VALUES
(1, '西红柿切块，鸡蛋打散', CONCAT('https://step-img.com/', UUID(), '.jpg'), 1),
(1, '热锅倒油，倒入鸡蛋炒熟盛出', CONCAT('https://step-img.com/', UUID(), '.jpg'), 2),
(2, '猪肉切块焯水', CONCAT('https://step-img.com/', UUID(), '.jpg'), 1),
(2, '锅中倒油，放入冰糖炒出糖色', CONCAT('https://step-img.com/', UUID(), '.jpg'), 2),
(3, '肉丝用料酒腌制', CONCAT('https://step-img.com/', UUID(), '.jpg'), 1),
(3, '葱姜蒜爆香，放入肉丝翻炒', CONCAT('https://step-img.com/', UUID(), '.jpg'), 2),
(4, '鸡肉切丁，用料酒腌制', CONCAT('https://step-img.com/', UUID(), '.jpg'), 1),
(4, '花生米炸熟备用', CONCAT('https://step-img.com/', UUID(), '.jpg'), 2),
(5, '豆腐切块焯水', CONCAT('https://step-img.com/', UUID(), '.jpg'), 1),
(5, '锅中倒油，放入豆瓣酱炒香', CONCAT('https://step-img.com/', UUID(), '.jpg'), 2);

-- 餐单收藏表 (menu_collection) - 生成10条随机数据
INSERT INTO menu_collection (user_id, menu_id) VALUES
('u1001', 2),
('u1001', 3),
('u1002', 1),
('u1002', 4),
('u1003', 5),
('u1003', 6),
('u1004', 7),
('u1004', 8),
('u1005', 9),
('u1005', 10);

-- 餐单分享表 (menu_share) - 生成10条随机数据
INSERT INTO menu_share (menu_id, user_id, share_channel) VALUES
(1, 'u1001', 'wechat'),
(2, 'u1002', 'wechat'),
(3, 'u1003', 'qq'),
(4, 'u1004', 'weibo'),
(5, 'u1005', 'wechat'),
(6, 'u1006', 'qq'),
(7, 'u1007', 'wechat'),
(8, 'u1008', 'weibo'),
(9, 'u1009', 'wechat'),
(10, 'u1010', 'qq');

-- 订单表 (order) - 生成10条随机数据
INSERT INTO `order` (order_no, user_id, order_time, finish_time, status, total_price, notes) VALUES
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1001', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), '不要辣'),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1002', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), '多放香菜'),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1003', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), ''),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1004', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), NULL, 'pending', ROUND(RAND()*100+50, 2), '尽快送达'),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1005', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), ''),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1006', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), '少盐'),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1007', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), NULL, 'pending', ROUND(RAND()*100+50, 2), ''),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1008', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), '不要香菜'),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1009', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*29) DAY), 'completed', ROUND(RAND()*100+50, 2), ''),
(CONCAT('ORD', DATE_FORMAT(NOW(), '%Y%m%d'), FLOOR(RAND()*10000)), 'u1010', DATE_SUB(NOW(), INTERVAL FLOOR(RAND()*30) DAY), NULL, 'pending', ROUND(RAND()*100+50, 2), '多放辣椒');

-- 订单明细表 (order_detail) - 生成10条随机数据
INSERT INTO order_detail (order_no, menu_id, menu_name, menu_price, quantity, subtotal) VALUES
((SELECT order_no FROM `order` WHERE id = 1), 1, '西红柿炒鸡蛋', 28.00, '1', 28.00),
((SELECT order_no FROM `order` WHERE id = 1), 2, '红烧肉', 48.00, '1', 48.00),
((SELECT order_no FROM `order` WHERE id = 2), 3, '鱼香肉丝', 38.00, '1', 38.00),
((SELECT order_no FROM `order` WHERE id = 3), 4, '宫保鸡丁', 36.00, '1', 36.00),
((SELECT order_no FROM `order` WHERE id = 4), 5, '麻婆豆腐', 22.00, '1', 22.00),
((SELECT order_no FROM `order` WHERE id = 5), 6, '清蒸鱼', 58.00, '1', 58.00),
((SELECT order_no FROM `order` WHERE id = 6), 7, '北京烤鸭', 128.00, '1', 128.00),
((SELECT order_no FROM `order` WHERE id = 7), 8, '水煮鱼', 68.00, '1', 68.00),
((SELECT order_no FROM `order` WHERE id = 8), 9, '回锅肉', 38.00, '1', 38.00),
((SELECT order_no FROM `order` WHERE id = 9), 10, '夫妻肺片', 42.00, '1', 42.00);

-- 计划表 (plan) - 生成10条随机数据
INSERT INTO plan (plan_date, meals, user_id) VALUES
(DATE_ADD(CURDATE(), INTERVAL 1 DAY), '[{"mealType":"breakfast","menuIds":[1,2]},{"mealType":"lunch","menuIds":[3,4]},{"mealType":"dinner","menuIds":[5,6]}]', 'u1001'),
(DATE_ADD(CURDATE(), INTERVAL 2 DAY), '[{"mealType":"breakfast","menuIds":[7,8]},{"mealType":"lunch","menuIds":[9,10]},{"mealType":"dinner","menuIds":[1,2]}]', 'u1002'),
(DATE_ADD(CURDATE(), INTERVAL 3 DAY), '[{"mealType":"breakfast","menuIds":[3,4]},{"mealType":"lunch","menuIds":[5,6]},{"mealType":"dinner","menuIds":[7,8]}]', 'u1003'),
(DATE_ADD(CURDATE(), INTERVAL 4 DAY), '[{"mealType":"breakfast","menuIds":[9,10]},{"mealType":"lunch","menuIds":[1,2]},{"mealType":"dinner","menuIds":[3,4]}]', 'u1004'),
(DATE_ADD(CURDATE(), INTERVAL 5 DAY), '[{"mealType":"breakfast","menuIds":[5,6]},{"mealType":"lunch","menuIds":[7,8]},{"mealType":"dinner","menuIds":[9,10]}]', 'u1005'),
(DATE_ADD(CURDATE(), INTERVAL 6 DAY), '[{"mealType":"breakfast","menuIds":[2,3]},{"mealType":"lunch","menuIds":[4,5]},{"mealType":"dinner","menuIds":[6,7]}]', 'u1006'),
(DATE_ADD(CURDATE(), INTERVAL 7 DAY), '[{"mealType":"breakfast","menuIds":[8,9]},{"mealType":"lunch","menuIds":[10,1]},{"mealType":"dinner","menuIds":[2,3]}]', 'u1007'),
(DATE_ADD(CURDATE(), INTERVAL 8 DAY), '[{"mealType":"breakfast","menuIds":[4,5]},{"mealType":"lunch","menuIds":[6,7]},{"mealType":"dinner","menuIds":[8,9]}]', 'u1008'),
(DATE_ADD(CURDATE(), INTERVAL 9 DAY), '[{"mealType":"breakfast","menuIds":[10,1]},{"mealType":"lunch","menuIds":[2,3]},{"mealType":"dinner","menuIds":[4,5]}]', 'u1009'),
(DATE_ADD(CURDATE(), INTERVAL 10 DAY), '[{"mealType":"breakfast","menuIds":[6,7]},{"mealType":"lunch","menuIds":[8,9]},{"mealType":"dinner","menuIds":[10,1]}]', 'u1010');

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;