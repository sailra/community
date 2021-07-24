package com.springstudy.community.dto;

import com.springstudy.community.model.User;
import lombok.Data;

@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer createId;
    private Integer number_of_comments;
    private Integer number_of_readers;
    private Integer number_of_likes;
    private String tag;
    private User user;
}
