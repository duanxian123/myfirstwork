package com.example.linghao.myfirstwork.shujuku;

import java.security.PublicKey;

import cn.bmob.v3.BmobObject;
public class Post extends BmobObject {



    private String content;// 帖子内容

    private String author;//帖子的发布者，这里体现的是一对一的关系，该帖子属于某个用户
public String getContent(){
        return content;
    }
    public  void setContent(String content){
        this.content=content;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }

}