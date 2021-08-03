package com.ccic.config.mybatis;

/**
 * @description gender enumerate (please do not modify ！)
 */
public enum Gender {

	MALE("男", "M"), FEMALE("女", "F");

	private String display;

	private String value;

	private Gender(String display, String value) {
		this.display = display;
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}
	
	public static String getDisplay(String value) {
		for (Gender gender: Gender.values()) {
			if (gender.getValue().equals(value)) {
				return gender.display;
			}
		}
		return null;
	}
	
	public String getValue() {
		return value;
	}
	
	public static String getValue(String display) {
		for (Gender gender: Gender.values()) {
			if (gender.getDisplay().equals(display)) {
				return gender.value;
			}
		}
		return null;
	}
	
	public static Gender displayOf(String display) {
		if (display == null) {
			throw new NullPointerException("display is null");
		}
		for (Gender gender: Gender.values()) {
			if (gender.getDisplay().equals(display)) {
				return gender;
			}
		}
		throw new IllegalArgumentException("No enum display " + display);
	}
	
	public static Gender newValueOf(String value) {
		if (value == null) {
			throw new NullPointerException("value is null");
		}
		for (Gender gender: Gender.values()) {
			if (gender.getValue().equals(value)) {
				return gender;
			}
		}
		throw new IllegalArgumentException("No enum new value " + value);
	}
}