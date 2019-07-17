package com.weiyang.community.service;


import com.github.pagehelper.PageHelper;
import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.mapper.QuestionMapper;
import com.weiyang.community.mapper.UserMapper;
import com.weiyang.community.model.Question;
import com.weiyang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;




   /* public List<QuestionDTO> list( ) {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }*/

    public List<QuestionDTO> getAll() {

        return questionMapper.list();
    }
}
