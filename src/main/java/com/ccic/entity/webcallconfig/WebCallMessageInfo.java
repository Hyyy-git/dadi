package com.ccic.entity.webcallconfig;

import java.io.Serializable;

/**
 * Created by TaoHang on 2019/8/16.
 */
public class WebCallMessageInfo implements Serializable {

    private static final long serialVersionUID = 123456789012L;
    /**
     * 数据库主键id
     */
    private String id;
    /**
     * 模板编号
     */
    private String templateId;
    /**
     * 外显号码
     */
    private String clid;
    /**
     * 动态信息
     */
    private String dynamicMessage;
    /**
     * 返回信息
     */
    private String description;
    /**
     * 调用成功唯一标识
     */
    private String requestUniqueId;
    /**
     * 返回状态
     */
    private String resultCode;
    /**
     * url请求信息串
     */
    private String urlInfo;
    /**
     * 插入时间
     */
    private String inputTime;
    /**
     * 更新时间
     */
    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getDynamicMessage() {
        return dynamicMessage;
    }

    public void setDynamicMessage(String dynamicMessage) {
        this.dynamicMessage = dynamicMessage;
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

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getUrlInfo() {
        return urlInfo;
    }

    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "WebCallMessageInfo{" +
                "id='" + id + '\'' +
                ", templateId='" + templateId + '\'' +
                ", clid='" + clid + '\'' +
                ", dynamicMessage='" + dynamicMessage + '\'' +
                ", descripton='" + description + '\'' +
                ", requestUniqueId='" + requestUniqueId + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", urlInfo='" + urlInfo + '\'' +
                ", inputTime='" + inputTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
