package com.weiyang.community.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.dto.QuestionUserDTO;
import com.weiyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                        @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize
                        ) {
        PageHelper.startPage(pageNum,pageSize);
        List<QuestionDTO> list = questionService.getAll();
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

}
