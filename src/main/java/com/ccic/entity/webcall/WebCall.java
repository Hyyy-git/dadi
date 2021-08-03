package com.ccic.entity.webcall;


import java.io.Serializable;

/**
 * Created by TaoHang on 2019/8/13.
 */
public class WebCall implements Serializable {

    private static final long serialVersionUID = 1L;
    /***
     * 验证类型
     */
    private int validateType;

    /**
     * 呼叫中心编号
     */
    private int enterpriseId;

    /**
     * 当前时间戳
     */
    private long timestamp;

    /**
     * 验证值
     */
    private String sign;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 回呼接通后进入的ivrId
     */
    private int ivrId;

    /**
     * 1.普通话 2.粤语 4.标贝TTS 5.普通话-甜美女音 默认为1
     */
    private String vid;
    /**
     * 动态附带参数（值：user_message）以逗号分隔name1,name2… name必须为字符或数字，参数名建议使用user_开头
     */
    private String paramNames;

    /**
     * 动态参数类型(默认：2)
     */
    private String paramTypes;
    /**
     * user_message(描述文本)
     */
    private String user_message;
    /**
     * user_message(描述文本)
     */
    private String user_message1;

    public int getValidateType() {
        return validateType;
    }

    public void setValidateType(int validateType) {
        this.validateType = validateType;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIvrId() {
        return ivrId;
    }

    public void setIvrId(int ivrId) {
        this.ivrId = ivrId;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getParamNames() {
        return paramNames;
    }

    public void setParamNames(String paramNames) {
        this.paramNames = paramNames;
    }

    public String getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(String paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }

    public String getUser_message1() {
        return user_message1;
    }

    public void setUser_message1(String user_message1) {
        this.user_message1 = user_message1;
    }

    @Override
    public String toString() {
        return "WebCall{" +
                "validateType='" + validateType + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                ", tel='" + tel + '\'' +
                ", ivrId=" + ivrId +
                ", vid='" + vid + '\'' +
                ", paramNames='" + paramNames + '\'' +
                ", paramTypes='" + paramTypes + '\'' +
                ", user_message='" + user_message + '\'' +
                ", user_message1='" + user_message1 + '\'' +
                '}';
    }
}
