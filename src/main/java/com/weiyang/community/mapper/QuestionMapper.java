package com.weiyang.community.mapper;

import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void creat(Question question);


    @Select("select q.title,q.description,q.comment_count,q.view_count,q.gmt_create,u.avatar_url FROM question AS q,user AS u WHERE q.creator=u.id")
    List<QuestionDTO> list();




}
