package com.ccic.config.domain;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 分页返回前端对象
 * 分页功能，接收参数、查询分页均无变化
 * 返回分页对象的时候采用此对象 转json返回
 * 例如: 返回类型String
 *      PageInfo<Customer> custs = custService.queryUserPage(cust);
 *      PageResult page = new PageResult(custs.getTotal(),custs.getList());
 *      return PageResult.toJSONObject(page);
 */
public class PageResult {

    /**返回状态*/
    private boolean success = false;
    /**返回消息*/
    private String message;
    /**返回条数*/
    private long total;
    /**返回list对象*/
    private List<?> rows;
    /**返回参数（仅字符串）*/
    private String params;
    /**返回对象（多个）*/
    private Map<String, Object> objects = new HashMap<String, Object>();

    public PageResult(){};

    public PageResult(long total, List<?> rows) {
        this.success = true;
        this.total = total;
        this.rows = rows;
    }

    public PageResult(String params, long total, List<?> rows) {
        this.params = params;
        this.success = true;
        this.total = total;
        this.rows = rows;
    }

    public PageResult(long total, List<?> rows, Map<String, Object> objects) {
        this.success = true;
        this.total = total;
        this.rows = rows;
        this.objects = objects;
    }

    public PageResult(boolean success, String message, long total, List<?> rows) {
        this.success = success;
        this.message = message;
        this.total = total;
        this.rows = rows;
    }

    public static PageResult success(String params, long total, List<?> rows) {
        return new PageResult(true, params, total, rows);
    }

    public static PageResult success(long total, List<?> rows) {
        return new PageResult(total, rows);
    }

    public static PageResult error(String message) {
        return new PageResult(false, message, 0, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Object> objects) {
        this.objects = objects;
    }

    /**
     * @param json
     * @return
     * @description To JSON Object
     */
    public static String toJSONObject(PageResult json) {
        return JSONObject.fromObject(json).toString();
    }

}
