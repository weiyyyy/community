package com.weiyang.community.service;


import com.github.pagehelper.PageHelper;
import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.dto.QuestionUserDTO;
import com.weiyang.community.exception.CustomizeException;
import com.weiyang.community.mapper.QuestionMapper;
import com.weiyang.community.mapper.UserMapper;
import com.weiyang.community.model.Question;
import com.weiyang.community.model.QuestionExample;
import com.weiyang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list(Long userId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<QuestionDTO> questionDTOS = questionMapper.listByUserId(userId);
        return questionDTOS;
    }


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

    public QuestionUserDTO getById(Long id) {
        Question question=questionMapper.getById(id);
        if (question==null){
            throw new CustomizeException("你找的问题不存在，请重新查找");
        }
        QuestionUserDTO questionUserDTO = new QuestionUserDTO();
        BeanUtils.copyProperties(question,questionUserDTO);
        User user = userMapper.findById(question.getCreator());
        questionUserDTO.setUser(user);
        return questionUserDTO;

    }

    public void createOrUpdate(Question question) {

        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.creat(question);
        }else{
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());


            questionMapper.update(question);
        }
    }
}
