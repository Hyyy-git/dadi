package com;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * Created by THH on 2019/8/6.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.ccic.dao")
public class AlarmCenterServerApplication extends SpringBootServletInitializer {


	private static Logger log = LoggerFactory.getLogger(AlarmCenterServerApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AlarmCenterServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AlarmCenterServerApplication.class, args);
		log.info("||======服务启动成功======||");
	}
}
