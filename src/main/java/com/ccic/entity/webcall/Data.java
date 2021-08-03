package com.ccic.entity.webcall;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TaoHang on 2019/8/29.
 */
public class Data implements Serializable {

    private static final long serialVersionUID = 4655781234L;
    private List<CallRecordInfo> cdrList;

    public List<CallRecordInfo> getCdrList() {
        return cdrList;
    }

    public void setCdrList(List<CallRecordInfo> cdrList) {
        this.cdrList = cdrList;
    }

    @Override
    public String toString() {
        return "Data{" +
                "cdrList=" + cdrList +
                '}';
    }
}
