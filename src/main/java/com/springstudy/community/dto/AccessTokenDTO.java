package com.springstudy.community.dto;

/*
封装了github授权token需要的信息
*/

import lombok.Data;

@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;

}
