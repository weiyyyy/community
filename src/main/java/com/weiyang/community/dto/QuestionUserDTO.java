package com.weiyang.community.dto;

import com.weiyang.community.model.User;
import lombok.Data;

@Data
public class QuestionUserDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
