package com.weiyang.community.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.mapper.UserMapper;
import com.weiyang.community.model.Question;
import com.weiyang.community.model.User;
import com.weiyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum
                        ) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PageHelper.startPage(pageNum,5);
        List<QuestionDTO> list = questionService.getAll();
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

}
