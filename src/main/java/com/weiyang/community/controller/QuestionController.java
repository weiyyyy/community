package com.weiyang.community.controller;

import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.dto.QuestionUserDTO;
import com.weiyang.community.mapper.QuestionMapper;
import com.weiyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sun.dc.pr.PRError;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionUserDTO questionUserDTO = questionService.getById(id);
        model.addAttribute("question", questionUserDTO);
        return "question";
    }
}
