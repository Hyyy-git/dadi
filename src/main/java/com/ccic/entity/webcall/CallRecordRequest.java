package com.ccic.entity.webcall;

import java.io.Serializable;

/**
 * Created by TaoHang on 2019/8/28.
 */
public class CallRecordRequest implements Serializable {

    private static final long serialVersionUID = 1784L;

    /**
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
    private int timestamp;
    /**
     * 验证值
     */
    private String sign;

    /**
     * 请求唯一id
     */
    private String requestUniqueId;

    /**
     * 查询开始时间
     */
    private int startTime;

    /**
     * 查询结束时间
     */
    private int endTime;

    /**
     * 从第几条开始取
     */
    private int start;

    /**
     * 需要取出的数据条数
     */
    private int limit;


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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CallRecord{" +
                "validateType=" + validateType +
                ", enterpriseId=" + enterpriseId +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                ", requestUniqueId='" + requestUniqueId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", start='" + start + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
