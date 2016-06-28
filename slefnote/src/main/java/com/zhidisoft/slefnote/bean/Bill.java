package com.zhidisoft.slefnote.bean;

/**
 * Created by Administrator on 2016/6/21.
 */
public class Bill {
    private int imageId;
    private String billName;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Bill() {
    }

    public Bill(int imageId, String billName) {
        this.imageId = imageId;
        this.billName = billName;
    }
}
