package com.ccic.component;

import com.ccic.service.BaseService;
import com.ccic.service.webcall.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by TaoHang on 2019/8/27.
 */
@Component
@Configuration
@EnableScheduling
public class WebCallTimingTasks extends BaseService {
    @Autowired
    @SuppressWarnings("all")
    SendMessageService sendMessageService;

    @Scheduled(cron = "0 0 1 * * ?")
    private void updateWebMessageTasks() {
        log.info("开启通话状态定时任务");
        sendMessageService.getCallRecord();
        log.info("结束通话状态定时任务");
    }

}
