package com.zhidisoft.slefnote.bean;

/**
 * Created by Administrator on 2016/6/24.
 */
public class BillStore {
    private int useCat;
    private int imgId;
    private String useWay;
    private double moneyNum;

    public BillStore() {
    }

    public BillStore(int useCat, int imgId, String useWay, double moneyNum) {

        this.useCat = useCat;
        this.imgId = imgId;
        this.moneyNum = moneyNum;
        this.useWay = useWay;
    }

    public int getUseCat() {

        return useCat;
    }

    public void setUseCat(int useCat) {
        this.useCat = useCat;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getUseWay() {
        return useWay;
    }

    public void setUseWay(String useWay) {
        this.useWay = useWay;
    }

    public double getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(double moneyNum) {
        this.moneyNum = moneyNum;
    }
}
