package com.springstudy.community.mapper;

import com.springstudy.community.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostsMapper {

    @Insert("insert into online_posts(title, content, gmt_create, gmt_modified, createId, tag) values(#{title}, #{content}, #{gmt_create}, #{gmt_modified}, #{createId}, #{tag});")
    void createPost(Post post);

    @Select("select * from online_posts limit #{limitSize}, #{size};")
    List<Post> list(@Param(value = "limitSize") Integer limitSize,@Param(value = "size") Integer size);

    @Select("select count(1) from online_posts;")
    Integer countNumber();
}
