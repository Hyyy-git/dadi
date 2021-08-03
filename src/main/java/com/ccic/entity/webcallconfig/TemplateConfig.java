package com.ccic.entity.webcallconfig;

import com.ccic.config.paginator.Paginator;

import java.io.Serializable;

/**
 * Created by TaoHang on 2019/8/16.
 */
public class TemplateConfig extends Paginator implements Serializable {

    private static final long serialVersionUID = 1234567890L;
    /**
     * 数据库主键id
     */
    private String id;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 部门编号
     */
    private String systemNumber;
    /**
     * 模板内容
     */
    private String templateInfo;
    /**
     * 联系人名称
     */
    private String contactsName;
    /**
     * 联系人电话
     */
    private String telephone;

    /**
     * 插入时间
     */
    private String inputTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 使用状态
     */
    private String state;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     *告警方式
     */
    private String systemInfoType;
    /**
     * 跟新人
     */
    private String updateUserId;
    /**
     *
     */
    private String timeOut;
    /**
     * 联系人邮箱
     */
    private String mail;
    /**
     * 联系人微信
     */
    private String wechat;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 内容相似度阈值
     */
    private Integer similarity;
    /**
     * 邮件类型
     */
    private String mailType;

    /**
     * 每页显示数量
     */
    private Integer pagesize;

    /**
     * 页码值
     */
    private Integer pagenum;


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

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getTemplateInfo() {
        return templateInfo;
    }

    public void setTemplateInfo(String templateInfo) {
        this.templateInfo = templateInfo;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getSystemInfoType() {
        return systemInfoType;
    }

    public void setSystemInfoType(String systemInfoType) {
        this.systemInfoType = systemInfoType;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    @Override
    public String toString() {
        return "TemplateConfig{" +
                "id='" + id + '\'' +
                ", templateId='" + templateId + '\'' +
                ", systemNumber='" + systemNumber + '\'' +
                ", templateInfo='" + templateInfo + '\'' +
                ", contactsName='" + contactsName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", inputTime='" + inputTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", state='" + state + '\'' +
                ", templateName='" + templateName + '\'' +
                ", systemInfoType='" + systemInfoType + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", timeOut='" + timeOut + '\'' +
                ", mail='" + mail + '\'' +
                ", wechat='" + wechat + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", similarity=" + similarity +
                ", mailType='" + mailType + '\'' +
                ", pagesize=" + pagesize +
                ", pagenum=" + pagenum +
                '}';
    }
}
