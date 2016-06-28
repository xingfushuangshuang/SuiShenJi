package com.zhidisoft.fragcomm.bean;

/**
 * Created by Administrator on 2016/6/17.
 */
public class Food {
    private int imgId;
    private String title;
    private String content;

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgId() {

        return imgId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
