package com.weiyang.community.dto;

import com.google.common.base.Converter;
import com.weiyang.community.model.Comment;
import com.weiyang.community.model.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
@Accessors(chain = true)
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
    private Integer commentCount;



    private static class CommentDTOConvert extends Converter<CommentDTO, Comment> {
        @Override
        protected Comment doForward(CommentDTO commentDTO) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO,comment);
            return comment;
        }

        @Override
        protected CommentDTO doBackward(Comment comment) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            return commentDTO;
        }
    }
}
