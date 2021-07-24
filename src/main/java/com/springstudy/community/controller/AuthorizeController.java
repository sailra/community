package com.springstudy.community.controller;

import com.springstudy.community.dto.AccessTokenDTO;
import com.springstudy.community.dto.GithubUser;
import com.springstudy.community.provider.LoginProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    //自动实例化
    @Autowired
    private LoginProvider loginProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUrl;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = loginProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = loginProvider.getUser(accessToken);
        System.out.println(githubUser.getName() + ":" + githubUser.getId());
        return "index";
    }
}
