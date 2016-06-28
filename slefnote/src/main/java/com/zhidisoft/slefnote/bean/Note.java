package com.zhidisoft.slefnote.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/20.
 */
public class Note implements Serializable{//实现Serializable接口 以便intent可以直接传递实体bean
    private String title;
    private String content;
    private String time;

    public Note() {
    }

    public Note(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
