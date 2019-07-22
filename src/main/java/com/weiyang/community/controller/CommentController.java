package com.weiyang.community.controller;

import com.weiyang.community.dto.CommentDTO;
import com.weiyang.community.mapper.CommentMapper;
import com.weiyang.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @PostMapping(value = "/comment")
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        Map<Object,Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("message","成功");
        return objectObjectMap;

    }
}
