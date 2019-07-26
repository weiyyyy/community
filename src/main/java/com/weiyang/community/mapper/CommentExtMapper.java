package com.weiyang.community.mapper;

import com.weiyang.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}