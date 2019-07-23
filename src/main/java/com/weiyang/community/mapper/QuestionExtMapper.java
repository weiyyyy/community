package com.weiyang.community.mapper;

import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.model.Question;
import com.weiyang.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);

}