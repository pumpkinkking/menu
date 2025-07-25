-- 为菜单表添加卡路里字段
ALTER TABLE menu
ADD COLUMN calories DECIMAL(6,1) NULL COMMENT '卡路里含量(千卡)';

-- 可选：如果需要设置默认值为0
-- ALTER TABLE menu MODIFY COLUMN calories DECIMAL(6,1) NOT NULL DEFAULT 0 COMMENT '卡路里含量(千卡)';

-- 可选：如果需要添加索引
-- CREATE INDEX idx_menu_calories ON menu(calories);