package com.ccic.entity.webcall;

import java.io.Serializable;

/**
 * Created by TaoHang on 2019/8/13.
 */
public class WebCallResult implements Serializable {

    private static final long serialVersionUID = 12L;

    /**
     * 返回状态（0成功、-1失败）
     */
    private int result;
    /**
     * 返回结果描述
     */
    private String description;
    /**
     * 请求唯一标识，只有提交成功时才返回
     */
    private String requestUniqueId;
    /**
     * 外显号码，只有提交成功时才返回此参数
     */
    private String clid;
    /**
     * 错误码,当返回result=-1时，返回该参数
     */
    private int errorCode;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "WebCallResult{" +
                "result=" + result +
                ", description='" + description + '\'' +
                ", requestUniqueId='" + requestUniqueId + '\'' +
                ", clid='" + clid + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
