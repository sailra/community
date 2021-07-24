package com.springstudy.community.provider;

import com.alibaba.fastjson.JSON;
import com.springstudy.community.dto.AccessTokenDTO;
import com.springstudy.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

/*登录工具，负责向github索要token和用户信息*/
@Component
public class LoginProvider {

    /*
    * 将AccessTokenDTO对象转化成json格式，向github发送，获取access_token
    * */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        String url = "https://github.com/login/oauth/access_token";
        MediaType mediaType = MediaType.Companion.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.Companion.create(JSON.toJSONString(accessTokenDTO),mediaType);
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*发送索要到的access_token获取对应用户信息*/
    public GithubUser getUser(String accessToken){
        String url = "https://api.github.com/user";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "token "+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            GithubUser githubUser = JSON.parseObject(json, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
