package com.ccic.entity.webcall;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TaoHang on 2019/8/28.
 */
public class CallRecordResult implements Serializable {

    private static final long serialVersionUID = 178412L;

    private String result;

    private String description;

    private Data data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CallRecordResult{" +
                "result='" + result + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
