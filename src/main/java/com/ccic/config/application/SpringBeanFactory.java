package com.ccic.config.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.beans.Introspector;

@Component
public class SpringBeanFactory implements ApplicationContextAware {

	private static ApplicationContext context;

	public SpringBeanFactory() {
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringBeanFactory.context = context;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name, Class<T> clazz) {
		return (T) getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return getBean(Introspector.decapitalize(clazz.getSimpleName()), clazz);
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

}
