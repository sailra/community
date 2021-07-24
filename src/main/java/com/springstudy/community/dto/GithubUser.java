package com.springstudy.community.dto;

/*
* git用户实体类
* */

import lombok.Data;

@Data
public class GithubUser {

    private String name;
    private long id;
    private String bio;
    private String avatar_url;

}
