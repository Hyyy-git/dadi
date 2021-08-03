package com.ccic.config.domain;


import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 返回前端对象
 * 前端同步、异步请求后端数据，返回前端JSONResult对象
 * 返回类型JSONResult或者String(使用自带方法将对象手动转String)类型
 * 		返回字符串，装入params，设置状态true，返回消息，和param
 * 		返回单个对象，装入obj
 *			List<Role> roleList = roleService.queryAllRole(role);
 *			return JSONResult.success("success", roleList);
 * 		返回多个对象，装入objects
 * 			Map<String, Object> objects = new HashMap<>();
 *			List<Role> roleList = roleService.queryAllRole(role);
 *			List<Company> companyList = companyService.queryCompanyList(company);
 *			objects.put("roleList", roleList);
 *			objects.put("companyList", companyList);
 *			return JSONResult.success("success", objects);
 * 		返回失败，必须置入失败消息
 * 			return JSONResult.error("错误信息");
 */
public class JSONResult {

	/**返回状态*/
	private boolean success = false;
	/**返回消息*/
	private String message;
	/**返回参数（近字符串）*/
	private String params;
	/**返回对象（单个）*/
	private Object obj;
	/**返回对象（多个）*/
	private Map<String, Object> objects = new HashMap<String, Object>();

	public JSONResult() {
	}

	public JSONResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public JSONResult(boolean success, Object obj, String message) {
		this(success, message);
		this.obj = obj;
	}

	public JSONResult(boolean success, String params, String message) {
		this(success, message);
		this.params = params;
	}

	public static JSONResult success(String message) {
		return new JSONResult(true, message);
	}

	public static JSONResult success(String message, Object object) {
		return new JSONResult(true, object, message);
	}

	public static JSONResult success(String message, Object... object) {
		return new JSONResult(true, object, message);
	}

	public static JSONResult error(String message) {
		return new JSONResult(false, message);
	}

	public static JSONResult error(String params, String message) {
		return new JSONResult(false, params, message);
	}

	public static JSONResult error(String message, Object object) {
		return new JSONResult(false, object, message);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getObjects() {
		return objects;
	}

	public void setObjects(Map<String, Object> objects) {
		this.objects = objects;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setObjAndSuccess(Object obj) {
		this.success = (null != obj);
		this.obj = obj;
	}

	public void setObjects(String key, Object object) {
		objects.put(key, object);
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @description To JSON Object
	 * @param json
	 * @return
	 */
	public static JSONObject toJSONObject(JSONResult json) {
		return JSONObject.fromObject(json);
	}

}
