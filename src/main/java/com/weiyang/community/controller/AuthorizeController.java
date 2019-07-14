package com.weiyang.community.controller;

import com.weiyang.community.dto.AccessTtokenDTO;
import com.weiyang.community.dto.GithubUser;
import com.weiyang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        AccessTtokenDTO accesSTtokenDTO = new AccessTtokenDTO();
        accesSTtokenDTO.setClient_id(clientId);
        accesSTtokenDTO.setClient_secret(clientSecret);
        accesSTtokenDTO.setCode(code);
        accesSTtokenDTO.setRedirect_uri(redirecturi);
        accesSTtokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accesSTtokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
