package com.ccic.config.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @description 返回结果
 */
public interface Result extends Serializable {

	/** result code */
	String SUCCESS = "success";

	/**
	 * 请求是否成功。
	 * 
	 * @return 如果成功，则返回<code>true</code>
	 */
	boolean isSuccess();

	/**
	 * 设置请求成功标志。
	 * 
	 * @param success
	 *            成功标志
	 */
	void setSuccess(boolean success);

	/**
	 * 获取返回码
	 * 
	 * @return 返回码
	 */
	String getResultCode();

	/**
	 * 设置返回码
	 * 
	 * @param code
	 */
	void setResultCode(String code);

	/**
	 * 取得model对象
	 * 
	 * @param key
	 *            字符串key
	 * @return model对象
	 */
	Object getModel(String key);

	/**
	 * 设置model对象。
	 * 
	 * @param key
	 *            字符串key
	 * @param model
	 *            model对象
	 */
	void setModel(String key, Object model);

	/**
	 * 设置model对象。
	 * 
	 * @param clazz
	 *            字符串key
	 * @param model
	 *            model对象
	 */
	void setModel(Class<?> clazz, Object model);

	/**
	 * 取得所有model对象。
	 * 
	 * @return model对象表
	 */
	Map<String, Object> getModels();

	/**
	 * <p>
	 * 获取特定类型的 model 对象
	 * </p>
	 * 
	 * @param <T>
	 * @param key
	 *            字符串 key
	 * @param clazz
	 *            数据类型
	 * @return
	 */
	<T> T getModel(String key, Class<T> clazz);

	<T> T getModel(Class<T> clazz);

}
