package com.weiyang.community.controller;

import com.weiyang.community.dto.QuestionUserDTO;
import com.weiyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionUserDTO questionUserDTO = questionService.getById(id);
        questionService.incView(id);
        model.addAttribute("question", questionUserDTO);
        return "question";
    }
}
