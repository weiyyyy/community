package com.weiyang.community.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
