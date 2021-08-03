package com.ccic.service.alarm.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccic.config.exception.DaoException;
import com.ccic.dao.alarm.AlarmInfoRecordDao;
import com.ccic.entity.alarm.AlarmRecordInfo;
import com.ccic.entity.webcall.SendMessage;
import com.ccic.service.alarm.AlarmInfoService;
import com.ccic.utils.AlarmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
public class AlarmInfoServiceImpl implements AlarmInfoService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private AlarmInfoRecordDao alarmInfoRecordDao;


    @Override
    public String saveAlarmInfo(SendMessage message) {

        AlarmRecordInfo alarmRecordInfo = new AlarmRecordInfo();
        String id = null;
        try {
            id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            alarmRecordInfo.setId(id);
            /*JSONObject jsonObject = JSON.parseObject(message);
            log.info("TemplateId:{}",jsonObject.getString("templateId"));
            alarmRecordInfo.setTemplateId(jsonObject.getString("templateId"));
            log.info("Params:{}",jsonObject.getString("params"));
            alarmRecordInfo.setParamsInfo(jsonObject.getString("params"));
            Integer num = alarmInfoRecordDao.addAlarmInfo(alarmRecordInfo);
            log.info("saveResult:{}",num+"---成功保存到数据库");*/
            alarmRecordInfo.setTemplateId(message.getTemplateId());
            String params = AlarmUtils.INSTANCE.mapToJsonString(message.getParams());
            alarmRecordInfo.setParamsInfo(params);
            Integer num = alarmInfoRecordDao.addAlarmInfo(alarmRecordInfo);
            log.info("saveResult:{}",num+"---成功保存到数据库");
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public AlarmRecordInfo getLastRecordByTemplateId(String templateId) {
        AlarmRecordInfo alarmRecordInfo = new AlarmRecordInfo();
        try {
            alarmRecordInfo = alarmInfoRecordDao.findLastRecordByTemplateId(templateId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return alarmRecordInfo;
    }

}
