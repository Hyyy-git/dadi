package com.ccic.controller.webcall;

import com.ccic.component.Producer;
import com.ccic.config.domain.JSONResult;
import com.ccic.entity.webcall.SendMessage;
import com.ccic.service.webcall.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TaoHang on 2019/8/13.
 */

@RestController
@RequestMapping("/sendMessage")
public class SendMessageController {
    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private Producer producer;

    /**
     * 告警信息发送
     *
     * @param webCallMessage
     * @return
     */
    @RequestMapping(value = "/sendWarningInfo")
    public JSONResult sendWarningInfo(@RequestBody SendMessage webCallMessage) {
        try {
            sendMessageService.sendWarningInfo(webCallMessage);
            return JSONResult.success("success");
        } catch (Exception e) {
            return JSONResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/send")
    public JSONResult send(@RequestBody SendMessage webCallMessage) {
        try {
            producer.send(webCallMessage);
            return JSONResult.success("success", true);
        } catch (Exception e) {
            return JSONResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/sendMail")
    public JSONResult sendMail(@RequestBody SendMessage webCallMessage) {
        try {
//            webCallService.sendMail(webCallMessage);
            return JSONResult.success("success", true);
        } catch (Exception e) {
            return JSONResult.error(e.getMessage());
        }
    }



    /*@RequestMapping(value = "/query")
    public JSONResult query() {
        try {
            List<CallRecordInfo> result = webCallService.getCallRecord();
            return JSONResult.success("success", result);
        } catch (Exception e) {
            return JSONResult.error(e.getMessage());
        }
    }*/
}
