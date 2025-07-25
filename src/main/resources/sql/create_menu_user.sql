-- 创建用户菜单关联表
CREATE TABLE menu_user (
    menu_id BIGINT NOT NULL COMMENT '菜单ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    category_id BIGINT COMMENT '分类ID',
    price DECIMAL(10,2) NOT NULL COMMENT '价格',
    create_user BIGINT COMMENT '创建者ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    -- 复合主键定义
    PRIMARY KEY (menu_id, user_id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户菜单关联表';

-- 如需创建索引可添加以下语句
-- CREATE INDEX idx_menu_user_category ON menu_user(category_id);
-- CREATE INDEX idx_menu_user_price ON menu_user(price);