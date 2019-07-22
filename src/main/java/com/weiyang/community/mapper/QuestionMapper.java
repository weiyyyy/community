package com.weiyang.community.mapper;

import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.model.Question;
import com.weiyang.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {

    long countByExample(QuestionExample example);


    int deleteByExample(QuestionExample example);


    int deleteByPrimaryKey(Integer id);


    int insert(Question record);


    int insertSelective(Question record);


    List<Question> selectByExampleWithBLOBs(QuestionExample example);


    List<QuestionDTO> selectByExample(QuestionExample example);


    Question selectByPrimaryKey(Long id);


    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);


    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);


    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);


    int updateByPrimaryKeySelective(Question record);


    int updateByPrimaryKeyWithBLOBs(Question record);


    int updateByPrimaryKey(Question record);

    List<QuestionDTO> getAll();
}