package com.ccic.entity.alarm;

import java.io.Serializable;
import java.util.Date;

public class AlarmRecordInfo implements Serializable {

    private static final long serialVersionUID = 613825096206354523L;

    private String id;

    private String templateId;

    private String systemId;

    private String paramsInfo;

    private String templateInfo;

    private String failSendReason;

    private Integer sendFlag; // 1待发送，2发送成功，3发送失败

    private Integer isValid;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getParamsInfo() {
        return paramsInfo;
    }

    public void setParamsInfo(String paramsInfo) {
        this.paramsInfo = paramsInfo;
    }

    public String getTemplateInfo() {
        return templateInfo;
    }

    public void setTemplateInfo(String templateInfo) {
        this.templateInfo = templateInfo;
    }

    public String getFailSendReason() {
        return failSendReason;
    }

    public void setFailSendReason(String failSendReason) {
        this.failSendReason = failSendReason;
    }

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AlarmRecordInfo{" +
                "id='" + id + '\'' +
                ", templateId='" + templateId + '\'' +
                ", systemId='" + systemId + '\'' +
                ", paramsInfo='" + paramsInfo + '\'' +
                ", templateInfo='" + templateInfo + '\'' +
                ", failSendReason='" + failSendReason + '\'' +
                ", sendFlag=" + sendFlag +
                ", isValid=" + isValid +
                ", isDel=" + isDel +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
