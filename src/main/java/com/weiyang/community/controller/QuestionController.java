package com.weiyang.community.controller;

import com.weiyang.community.dto.CommentDTO;
import com.weiyang.community.dto.QuestionUserDTO;
import com.weiyang.community.enums.CommentTypeEnum;
import com.weiyang.community.service.CommentService;
import com.weiyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService ;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionUserDTO questionUserDTO = questionService.getById(id);

        List<CommentDTO> comments= commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        questionService.incView(id);
        model.addAttribute("question", questionUserDTO);
        model.addAttribute("comments", comments);

        return "question";
    }
}
