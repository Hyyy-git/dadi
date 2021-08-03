package com.ccic.service.alarm;

import com.ccic.entity.alarm.AlarmRecordInfo;
import com.ccic.entity.webcall.SendMessage;

public interface AlarmInfoService {

    /**
     * 将告警信息存库
     * @param message
     * @return
     */
    String saveAlarmInfo(SendMessage message);

    /**
     * 查询发送的最新模板
     * @param templateId
     * @return
     */
    AlarmRecordInfo getLastRecordByTemplateId(String templateId);
}
