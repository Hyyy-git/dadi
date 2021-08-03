package com.ccic.service.webcall;

import com.ccic.config.domain.JSONResult;
import com.ccic.entity.webcall.SendMessage;

/**
 * Created by TaoHang on 2019/8/13.
 */
public interface SendMessageService {

    /**
     * 发送告警信息
     *
     * @param webCallMessage
     * @return
     */
    JSONResult sendWarningInfo(SendMessage webCallMessage);

    /**
     * 查询告警通话记录
     *
     * @param
     * @return
     */
    void getCallRecord();

}
