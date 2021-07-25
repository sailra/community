package com.springstudy.community.controller;

import com.springstudy.community.dto.PageDTO;
import com.springstudy.community.dto.PostDTO;
import com.springstudy.community.mapper.PostsMapper;
import com.springstudy.community.mapper.UserMapper;
import com.springstudy.community.model.Post;
import com.springstudy.community.model.User;
import com.springstudy.community.service.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostService postService;


    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0) {
            for( Cookie cookie: cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        PageDTO pageList = postService.list(page, size);
        model.addAttribute("pageList", pageList);
        return "index";
    }
}
