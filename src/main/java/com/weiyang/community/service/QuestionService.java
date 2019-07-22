package com.weiyang.community.service;


import com.github.pagehelper.PageHelper;
import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.dto.QuestionUserDTO;
import com.weiyang.community.exception.CustomizeErrorCode;
import com.weiyang.community.exception.CustomizeException;
import com.weiyang.community.mapper.QuestionExtMapper;
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
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public List<QuestionDTO> list(Long userId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        List<QuestionDTO> questionDTOS = questionMapper.selectByExample(questionExample);
        return questionDTOS;
    }


   /* public List<QuestionDTO> list( ) {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }*/

    public List<QuestionDTO> getAll() {

        return questionMapper.getAll();
    }

    public QuestionUserDTO getById(Long id) {

        Question question=  questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw new CustomizeException(CustomizeErrorCode.QUSETION_NOT_FOUND);
        }
        QuestionUserDTO questionUserDTO = new QuestionUserDTO();
        BeanUtils.copyProperties(question,questionUserDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionUserDTO.setUser(user);
        return questionUserDTO;

    }

    public void createOrUpdate(Question question) {

        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.insert(question);
        }else{
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (update!=1){
                throw new CustomizeException(CustomizeErrorCode.QUSETION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
/*        Question question = questionMapper.selectByPrimaryKey(id);
        Question updateQuestion = new Question();
        updateQuestion.setViewCount(question.getViewCount()+1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andIdEqualTo(id);*/
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);

    }
}
