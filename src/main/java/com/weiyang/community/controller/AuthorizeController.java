package com.weiyang.community.controller;

import com.weiyang.community.dto.AccessTtokenDTO;
import com.weiyang.community.dto.GithubUser;
import com.weiyang.community.mapper.UserMapper;
import com.weiyang.community.model.User;
import com.weiyang.community.provider.GithubProvider;
import com.weiyang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secrt}")
    private String clientSecret;

    @Value("${github.redirect.url}")
    private String redirecturi;


    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTtokenDTO accesSTtokenDTO = new AccessTtokenDTO();
        accesSTtokenDTO.setClient_id(clientId);
        accesSTtokenDTO.setClient_secret(clientSecret);
        accesSTtokenDTO.setCode(code);
        accesSTtokenDTO.setRedirect_uri(redirecturi);
        accesSTtokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accesSTtokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null&&githubUser.getId()!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            //登陆成功，写cookie 和session
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
