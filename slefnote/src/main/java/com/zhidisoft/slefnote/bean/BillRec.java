package com.zhidisoft.slefnote.bean;

/**
 * Created by Administrator on 2016/6/22.
 */
public class BillRec {
    private int imgId;
    private String rightStr;
    private String leftStr;

    public BillRec() {
    }

    public BillRec(int imgId, String rightStr, String leftStr) {

        this.imgId = imgId;
        this.rightStr = rightStr;
        this.leftStr = leftStr;
    }

    public int getImgId() {

        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getRightStr() {
        return rightStr;
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
    }

    public String getLeftStr() {
        return leftStr;
    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
    }
}
