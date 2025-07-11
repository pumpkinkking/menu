package com.menu.menu.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MenuVO {
    private Long id;
    private String name;
    private String description;
    private String coverImageUrl;
    private Long categoryId;
    private Integer viewCount;
    private Integer collectCount;
    private Integer shareCount;
    private LocalDateTime createTime;
}