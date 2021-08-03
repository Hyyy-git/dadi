package com.ccic.dao.alarm;

import com.ccic.config.exception.DaoException;
import com.ccic.entity.alarm.AlarmRecordInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


/**
 * Created by Huangyu 2020/9/24.
 */
@Configuration
public interface AlarmInfoRecordDao {

    /**
     * 告警信息存储
     * @param alarmRecordInfo
     * @return
     * @throws DaoException
     */
    Integer addAlarmInfo(AlarmRecordInfo alarmRecordInfo) throws DaoException;

    /**
     * 查询发送的最新模板
     * @param templateId
     * @return
     */
    AlarmRecordInfo findLastRecordByTemplateId(String templateId) throws DaoException;

    /**
     * 更新邮件发送状态
     * @param id
     * @return
     * @throws DaoException
     */
    Integer updateSendFlagById(@Param("sendFlag") Integer sendFlag, @Param("id") String id) throws DaoException;
}
