package com.ccic.component;

import com.ccic.entity.Dto;
import com.ccic.entity.alarm.AlarmRecordInfo;
import com.ccic.entity.webcall.SendMessage;
import com.ccic.service.BaseService;
import com.ccic.service.alarm.AlarmInfoService;
import com.ccic.service.webcall.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public static final String TOPIC = "BUSI-SYS-ALARM";

    @Autowired
    private SendMessageService sendMessageService;

    @KafkaListener(topics = TOPIC)
    public void receive(String message) {
        log.info("received:{}", message);
        SendMessage sendMessage = Dto.fromJson(message, SendMessage.class);
        log.info("json to object process:{}",sendMessage);
        sendMessageService.sendWarningInfo(sendMessage);
    }

}
