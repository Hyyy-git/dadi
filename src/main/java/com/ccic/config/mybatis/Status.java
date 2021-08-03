package com.ccic.config.mybatis;

/**
 * @description common status enumerate (please do not modify ！)
 */
public enum Status {

	VALID("有效", "1"), INVALID("无效", "0");

	private String display;
	
	private String value;
	
	private Status(String display, String value) {
		this.display = display;
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}
	
	public static String getDisplay(String value) {
		for (Status status: Status.values()) {
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
		for (Status status: Status.values()) {
			if (status.getDisplay().equals(display)) {
				return status.value;
			}
		}
		return null;
	}
	
	public static Status displayOf(String display) {
		if (display == null) {
			throw new NullPointerException("display is null");
		}
		for (Status status: Status.values()) {
			if (status.getDisplay().equals(display)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No enum display " + display);
	}
	
	public static Status newValueOf(String value) {
		if (value == null) {
			throw new NullPointerException("value is null");
		}
		for (Status status: Status.values()) {
			if (status.getValue().equals(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException("No enum new value " + value);
	}
}