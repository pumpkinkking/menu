package com.menu.menu.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MenuVO {
    private Integer id;
    private String name;
    private String description;
    private String coverImageUrl;
    private Integer categoryId;
    private Integer viewCount;
    private Integer collectCount;
    private Integer shareCount;
    private LocalDateTime createTime;
}