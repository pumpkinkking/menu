## 用户表 (sys_user)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| user_id | varchar(32)  | 是 | NULL | 用户ID |

| open_id | varchar(255) | 否 | NULL | 微信openid |

| phone_num | varchar(20) | 否 | NULL | 手机号 |

| username | varchar(50) | 否 | NULL | 用户名 |

| avatar_url | varchar(255) | 否 | NULL | 头像URL |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**表注释**：用户信息表


## 食材表 (ingredient)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| ingredient_id | int | 是 | AUTO_INCREMENT | 食材ID |

| user_id | varchar(32) | 否 | NULL | 用户ID |

| name | varchar(100) | 否 | NULL | 食材名称 |

| quantity | int(11) | 否 | NULL | 数量 |

| unit | varchar(20) | 否 | NULL | 单位 |

| location | varchar(50) | 否 | NULL | 存放位置 |

| freshness | varchar(20) | 否 | NULL | 新鲜度 |

| product_time | datetime | 否 | CURRENT_TIMESTAMP | 生产时间 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**索引**：idx_user_id (user_id)

**表注释**：食材信息表

  

## 菜篮子表 (basket)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| basket_id | int | 是 | AUTO_INCREMENT | 菜篮子ID |

| user_id | varchar(32) | 否 | NULL | 用户ID |

| ingredient_id | varchar(32) | 否 | NULL | 食材ID |

| menu_id | varchar(32) | 否 | NULL | 菜品ID |

| quantity | int(11) | 否 | NULL | 数量 |

| unit | varchar(20) | 否 | NULL | 单位 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**索引**：idx_user_id (user_id), idx_ingredient_id (ingredient_id)

**表注释**： 菜篮子表


## 用户餐单表 (user_menu)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| user_id | varchar(32) | 是 | NULL | 用户ID |

| menu_id | varchar(32) | 是 | NULL | 餐单ID |

| sort_order | int(11) | 否 | 0 | 排序序号 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

| is_deleted | tinyint(4) | 否 | 0 | 删除标志(0-未删除,1-已删除) |

**表注释**：用户餐单表，记录用户下有哪些菜品
## 餐单表 (menu)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| menu_id | int | 是 | AUTO_INCREMENT | 餐单ID |

| menu_name | varchar(100) | 否 | NULL | 餐单名称 |

| description | text | 否 | NULL | 餐单描述 |

| cover_image_url | varchar(255) | 否 | NULL | 封面图片URL |

| thumbnail_url | varchar(255) | 否 | NULL | 封面缩略图URL |

| category_id | varchar(32) | 否 | NULL | 分类ID |

| user_id | varchar(32) | 否 | NULL | 用户ID |

| view_count | int(11) | 否 | 0 | 查看次数 |

| collect_count | int(11) | 否 | 0 | 收藏次数 |

| share_count | int(11) | 否 | 0 | 分享次数 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

| is_deleted | tinyint(4) | 否 | 0 | 删除标志(0-未删除,1-已删除) |

  

**索引**：idx_user_id (user_id), idx_category_id (category_id)

**表注释**：餐单表

  

## 餐单食材表 (menu_ingredient)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| id | int | 是 | AUTO_INCREMENT | 食材ID |

| menu_id | varchar(32) | 否 | NULL | 餐单ID |

| ingredient_id | varchar(32) | 否 | NULL | 食材ID |

| ingredient_name | varchar(100) | 否 | NULL | 食材名称 |

| quantity | varchar(20) | 否 | NULL | 食材数量 |

| unit | varchar(20) | 否 | NULL | 计量单位 |

| sort_order | int(11) | 否 | 0 | 排序序号 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

**索引**：idx_menu_id (menu_id)

**表注释**：餐单食材表

  

## 餐单步骤表 (menu_step)

  
| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| menu_step_id | int | 是 | AUTO_INCREMENT | 步骤ID |

| menu_id | varchar(32) | 否 | NULL | 餐单ID |

| content | text | 否 | NULL | 步骤内容 |

| image_url | varchar(255) | 否 | NULL | 步骤图片URL |

| sort_order | int(11) | 否 | 0 | 排序序号 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |
  

**索引**：idx_menu_id (menu_id)

**表注释**：餐单步骤表

  

## 餐单收藏表 (menu_collection)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| menu_collection_id | int | 是 | AUTO_INCREMENT | 收藏ID |

| user_id | varchar(32) | 否 | NULL | 用户ID |

| menu_id | varchar(32) | 否 | NULL | 餐单ID |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 收藏时间 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**索引**：idx_user_id (user_id), idx_menu_id (menu_id)

**唯一键**：uk_user_menu (user_id, menu_id)

**表注释**：餐单收藏表

  

## 餐单分享表 (menu_share)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| menu_share_id | int | 是 | AUTO_INCREMENT | 分享记录ID |

| menu_id | varchar(32) | 否 | NULL | 餐单ID |

| user_id | varchar(32) | 否 | NULL | 用户ID |

| share_channel | varchar(50) | 否 | NULL | 分享渠道 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 分享时间 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**索引**：idx_user_id (user_id), idx_menu_id (menu_id)

**表注释**：餐单分享表

  

## 订单表 (order)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| order_id | int | 是 | AUTO_INCREMENT | 订单ID |

| order_no | varchar(50) | 否 | NULL | 订单编号 |

| user_id | varchar(32) | 否 | NULL | 用户ID |

| order_time | datetime | 否 | NULL | 下单时间 |

| finish_time | datetime | 否 | NULL | 完成时间 |

| status | varchar(20) | 否 | NULL | 状态 |

| total_price | decimal(10,2) | 否 | NULL | 总价 |

| notes | varchar(500) | 否 | NULL | 备注 |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**索引**：idx_user_id (user_id), idx_order_no (order_no)

**表注释**：订单表

## 订单明细表 (order_detail)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| order_detail_id | int | 是 | AUTO_INCREMENT | 订单ID |

| order_no | varchar(50) | 否 | NULL | 订单编号 |

| menu_id | varchar(32) | 否 | NULL | 菜品ID |

| menu_name | varchar(100) | 否 | NULL | 菜品名称 |

| menu_price | decimal(10,2) | 否 | NULL | 菜品单价 |

| quantity | varchar(20) | 否 | NULL | 数量 |

| subtotal | decimal(10,2) | 否 | NULL | 小计金额（单价 × 数量） |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**索引**：idx_user_id (user_id), idx_order_no (order_no)

**表注释**：订单表

## 计划表 (plan)

  

| 字段名 | 数据类型 | 主键 | 默认值 | 注释 |

|--------|----------|------|--------|------|

| plan_id | int | 是 | AUTO_INCREMENT | 计划ID |

| plan_date | date | 否 | NOT NULL | 计划日期 |

| meals | json | 否 | NOT NULL | 餐食列表 |

| user_id | varchar(32) | 否 | NOT NULL | 用户id |

| create_time | datetime | 否 | CURRENT_TIMESTAMP | 创建时间 |

| update_time | datetime | 否 | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | 更新时间 |

  

**表注释**：计划表
