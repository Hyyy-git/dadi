package com.ccic.component;

import com.ccic.entity.Dto;
import com.ccic.entity.webcall.SendMessage;
import com.ccic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by TaoHang on 2019/8/21.
 */
@Component
public class Producer extends BaseService {
    public static final String TOPIC = "BUSI-SYS-ALARM";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(SendMessage webCallMessage) {
        ListenableFuture future = kafkaTemplate.send(TOPIC, Dto.toString(webCallMessage));
        future.addCallback(o -> log.info("sent:{}", webCallMessage), throwable -> log.error("send fail", throwable));
    }
}
