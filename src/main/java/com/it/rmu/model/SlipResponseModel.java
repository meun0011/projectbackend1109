package com.it.rmu.model;

public class SlipResponseModel {
    private Integer slipImgId;
    private Integer ordersId;
    private String slipImgPath;
    private String slipImgName;
    private byte[] slipImgData;
    private String status;
    public Integer getSlipImgId() {
        return slipImgId;
    }
    public void setSlipImgId(Integer slipImgId) {
        this.slipImgId = slipImgId;
    }
    public Integer getOrdersId() {
        return ordersId;
    }
    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }
    public String getSlipImgPath() {
        return slipImgPath;
    }
    public void setSlipImgPath(String slipImgPath) {
        this.slipImgPath = slipImgPath;
    }
    public String getSlipImgName() {
        return slipImgName;
    }
    public void setSlipImgName(String slipImgName) {
        this.slipImgName = slipImgName;
    }
    public byte[] getSlipImgData() {
        return slipImgData;
    }
    public void setSlipImgData(byte[] slipImgData) {
        this.slipImgData = slipImgData;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}