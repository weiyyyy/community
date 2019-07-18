package com.weiyang.community.controller;

import com.github.pagehelper.PageInfo;
import com.weiyang.community.dto.QuestionDTO;
import com.weiyang.community.model.User;
import com.weiyang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                          @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            List<QuestionDTO> list=questionService.list(user.getId(),pageNum,pageSize);
            PageInfo<QuestionDTO> pageInfo = new PageInfo<>(list);
            model.addAttribute("myQuestins",pageInfo);
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的最新回复");
        }
        return "profile";
    }
}
