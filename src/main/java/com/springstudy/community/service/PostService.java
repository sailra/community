package com.springstudy.community.service;

import com.springstudy.community.dto.PageDTO;
import com.springstudy.community.dto.PostDTO;
import com.springstudy.community.mapper.PostsMapper;
import com.springstudy.community.mapper.UserMapper;
import com.springstudy.community.model.Post;
import com.springstudy.community.model.User;
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

    public PageDTO list(Integer page, Integer size) {


        PageDTO pageDTO = new PageDTO();
        Integer total = postsMapper.countNumber();
        pageDTO.setPageInformation(total, page, size);

        if(page < 1){
            page = 1;
        }else if(page > pageDTO.getTotalPage()){
            page = pageDTO.getTotalPage();
        }

        Integer limitSize = size*(page-1);
        List<Post> postList = postsMapper.list(limitSize, size);
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : postList) {
            User user = userMapper.findById(post.getCreateId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        pageDTO.setPostList(postDTOList);
        return pageDTO;
    }

}
