package com.ccic.entity.webcall;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by TaoHang on 2019/8/19.
 */
public class SendMessage implements Serializable {

    private static final long serialVersionUID = 1955L;

    /**
     * 数据库id
     */
    private String id;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 外显号码，只有提交成功时才返回此参数
     */
    private String clId;
    /**
     * 动态消息值
     */
    private Map params;
    /**
     * 动态消息值
     */
    private String param;
    /**
     * 消息通知类型
     */
    private String [] sendTypes;
    /**
     * 返回结果描述
     */
    private String description;
    /**
     * 请求唯一标识，只有提交成功时才返回
     */
    private String requestUniqueId;
    /**
     * 返回状态（0成功、-1失败）
     */
    private String resultCode;
    /**
     * url请求信息
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

    /**
     * 是否获取第三方返回数据状态
     *
     * @return
     */
    private String state;

    /**
     * 请求系统编号
     *
     * @return
     */
    private String systemId;

    /**
     * 接听状态
     */
    private String callSuccess;

    /**
     *
     * @return
     */
    private String callNumber;

    /**
     * 告警类型
     * @return
     */
    private String messageType;

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

    public String getClId() {
        return clId;
    }

    public void setClId(String clId) {
        this.clId = clId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getCallSuccess() {
        return callSuccess;
    }

    public void setCallSuccess(String callSuccess) {
        this.callSuccess = callSuccess;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String[] getSendTypes() {
        return sendTypes;
    }

    public void setSendTypes(String[] sendTypes) {
        this.sendTypes = sendTypes;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "id='" + id + '\'' +
                ", templateId='" + templateId + '\'' +
                ", clId='" + clId + '\'' +
                ", params=" + params +
                ", param='" + param + '\'' +
                ", sendTypes=" + Arrays.toString(sendTypes) +
                ", description='" + description + '\'' +
                ", requestUniqueId='" + requestUniqueId + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", urlInfo='" + urlInfo + '\'' +
                ", inputTime='" + inputTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", state='" + state + '\'' +
                ", systemId='" + systemId + '\'' +
                ", callSuccess='" + callSuccess + '\'' +
                ", callNumber='" + callNumber + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
