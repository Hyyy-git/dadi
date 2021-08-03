package com.ccic.config.domain;

import java.util.HashMap;
import java.util.Map;

public class ResultSupport implements Result, java.io.Serializable {

	private static final long serialVersionUID = -5427837161273573297L;

	private boolean success = false;
	private String resultCode;
	private Map<String, Object> models = new HashMap<String, Object>(4);

	public ResultSupport() {
	}

	public ResultSupport(boolean success) {
		this.success = success;
	}

	public Object getModel(String key) {
		return getModels().get(key);
	}

	public Map<String, Object> getModels() {
		return models;
	}

	public String getResultCode() {
		return resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setModel(String key, Object model) {
		getModels().put(key, model);
	}

	public void setModel(Class<?> clazz, Object model) {
		getModels().put(clazz.getSimpleName(), model);
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setModels(Map<String, Object> models) {
		this.models = models;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getModel(String key, Class<T> clazz) {
		return (T) getModel(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getModel(Class<T> clazz) {
		return (T) getModel(clazz.getSimpleName());
	}

}
