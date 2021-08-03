package com.ccic.entity.webcall;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by TaoHang on 2019/8/16.
 */
public class TemplateConfig implements Serializable {

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
     * 模板名称
     */
    private String templateName;
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
     * 联系人邮箱
     */
    private String mail;
    /**
     * 联系人微信
     */
    private String weChat;
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
     * 设置告警消息超时时间
     */
    private String timeout;

    /**
     * 系统提示消息类型
     *
     * @return
     */
    private String systemInfoType;

    /**
     * message是否添加时间戳
     *
     * @return
     */
    private String timStamp;

    /**
     *内容相似度阈值
     * @return
     */
    private BigDecimal similarity;

    /**
     * 邮件文本类型
     */
    private String mailType;


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


    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getSystemInfoType() {
        return systemInfoType;
    }

    public void setSystemInfoType(String systemInfoType) {
        this.systemInfoType = systemInfoType;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getTimStamp() {
        return timStamp;
    }

    public void setTimStamp(String timStamp) {
        this.timStamp = timStamp;
    }

    public BigDecimal getSimilarity() {
        return similarity;
    }

    public void setSimilarity(BigDecimal similarity) {
        this.similarity = similarity;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    @Override
    public String toString() {
        return "TemplateConfig{" +
                "id='" + id + '\'' +
                ", templateId='" + templateId + '\'' +
                ", templateName='" + templateName + '\'' +
                ", systemNumber='" + systemNumber + '\'' +
                ", templateInfo='" + templateInfo + '\'' +
                ", contactsName='" + contactsName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", weChat='" + weChat + '\'' +
                ", inputTime='" + inputTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", state='" + state + '\'' +
                ", timeout='" + timeout + '\'' +
                ", systemInfoType='" + systemInfoType + '\'' +
                ", timStamp='" + timStamp + '\'' +
                ", similarity=" + similarity +
                ", mailType='" + mailType + '\'' +
                '}';
    }
}
