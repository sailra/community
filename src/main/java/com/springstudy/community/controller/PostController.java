package com.springstudy.community.controller;

import com.springstudy.community.mapper.PostsMapper;
import com.springstudy.community.model.Post;
import com.springstudy.community.model.User;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {


    @Autowired
    private PostsMapper postsMapper;


    @GetMapping("/post")
    public String getPost(){
        return "post";
    }

    @PostMapping("/post")
    public String publishPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){

        HttpSession session = request.getSession();
        Post post = new Post();
        User user = (User)session.getAttribute("user");
        if(user==null) {
            model.addAttribute("error", "用户未登录");
            return "post";
        }
        post.setTitle(title);
        post.setContent(content);
        post.setTag(tag);
        post.setCreateId(user.getId());
        post.setGmt_create(System.currentTimeMillis());
        post.setGmt_modified(post.getGmt_create());
        postsMapper.createPost(post);
        return "redirect:/";
    }
}
