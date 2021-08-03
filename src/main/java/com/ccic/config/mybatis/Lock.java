package com.ccic.config.mybatis;

/**
 * @description common status enumerate (please do not modify ！)
 */
public enum Lock {

	UNLOCK("未锁定", "1"), LOCKED("锁定", "0");

	private String display;

	private String value;

	private Lock(String display, String value) {
		this.display = display;
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}
	
	public static String getDisplay(String value) {
		for (Lock status: Lock.values()) {
			if (status.getValue().equals(value)) {
				return status.display;
			}
		}
		return null;
	}
	
	public String getValue() {
		return value;
	}
	
	public static String getValue(String display) {
		for (Lock status: Lock.values()) {
			if (status.getDisplay().equals(display)) {
				return status.value;
			}
		}
		return null;
	}
	
	public static Lock displayOf(String display) {
		if (display == null) {
			throw new NullPointerException("display is null");
		}
		for (Lock status: Lock.values()) {
			if (status.getDisplay().equals(display)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No enum display " + display);
	}
	
	public static Lock newValueOf(String value) {
		if (value == null) {
			throw new NullPointerException("value is null");
		}
		for (Lock status: Lock.values()) {
			if (status.getValue().equals(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No enum new value " + value);
	}
}