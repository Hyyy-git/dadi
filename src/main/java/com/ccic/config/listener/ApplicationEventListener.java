package com.ccic.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @Description: application event listener
 */
@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // listen the life cycle of Spring Boot
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            log.debug("初始化环境变量");
        } else if (event instanceof ApplicationPreparedEvent) {
            log.info("初始化环境变量完成");
        } else if (event instanceof ContextRefreshedEvent) {
            log.debug("应用刷新");
        } else if (event instanceof ApplicationReadyEvent) {
            log.info("应用已启动完成");
        } else if (event instanceof ContextStartedEvent) {
            log.debug("应用启动");
        } else if (event instanceof ContextStoppedEvent) {
            log.debug("应用停止");
        } else if (event instanceof ContextClosedEvent) {
            log.debug("应用关闭");
        } else {
        }
    }
}
