package com.ccic.entity.webcall;

import java.io.Serializable;

/**
 * Created by TaoHang on 2019/8/28.
 */
public class CallRecordInfo implements Serializable {

    private static final long serialVersionUID = 4655784L;

    private String callType;
    /**
     * 通话是否接通
     */
    private String status;
    private String clid;
    private String startTime;
    private String answerTime;
    private String requestUniqueId;
    private String customerNumber;

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String toString() {
        return "CallRecordInfo{" +
                "callType='" + callType + '\'' +
                ", status='" + status + '\'' +
                ", clid='" + clid + '\'' +
                ", startTime='" + startTime + '\'' +
                ", answerTime='" + answerTime + '\'' +
                ", requestUniqueId='" + requestUniqueId + '\'' +
                ", customerNumber='" + customerNumber + '\'' +
                '}';
    }
}
