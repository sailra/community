package com.springstudy.community.service;

import com.springstudy.community.dto.PostDTO;
import com.springstudy.community.mapper.PostsMapper;
import com.springstudy.community.mapper.UserMapper;
import com.springstudy.community.model.Post;
import com.springstudy.community.model.User;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PostsMapper postsMapper;

    public List<PostDTO> list() {
        List<Post> postList = postsMapper.list();
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : postList) {
            User user = userMapper.findById(post.getCreateId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
}
