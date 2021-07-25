package com.springstudy.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<PostDTO> postList;
    private boolean endExhibition;
    private boolean firstExhibition;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;



    public void setPageInformation(Integer total, Integer page, Integer size) {

        if(total% size == 0){
            this.totalPage = total / size;
        }else{
            this.totalPage = total/size + 1;
        }
        if(page < 1){
            page = 1;
        }else if(page > totalPage){
            page = totalPage;
        }

        this.page = page;
        pages.add(page);
        for(int i=1; i<=3; i++){
            if(page-i > 0){
                pages.add(0, page-i);
            }
            if(page+i <= totalPage){
                pages.add(page+i);
            }
        }


        //判断是否展示上一页
        if(page==1){
            firstExhibition = false;
        }else {
            firstExhibition = true;
        }

        //判断是否展示下一页
        if(page == totalPage){
            showNext = false;

        }else{
            showNext = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            endExhibition = false;
        }else{
            endExhibition = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }

    }
}
