package com.ccic.dao.webcall;

import com.ccic.config.exception.DaoException;
import com.ccic.entity.webcall.SendMessage;
import com.ccic.entity.webcall.TemplateConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by TaoHangon 2019/8/19.
 */
@Configuration
public interface WebCallDao {

    /**
     * 获取数据路序列
     *
     * @return
     * @throws DaoException
     */
    String getSerialNo() throws DaoException;

    /**
     * 查询模板配置
     *
     * @param templateId
     * @return
     * @throws DaoException
     */
    TemplateConfig queryTemplateConfig(@Param("templateId") String templateId) throws DaoException;

    /**
     * 告警信息存储
     * @param webCallMessage
     * @return
     * @throws DaoException
     */
    Integer addMessageInfo(SendMessage webCallMessage) throws DaoException;

    /**
     * 告警信息状态修改
     * @param webCallMessage
     * @return
     * @throws DaoException
     */
    Integer updateMessageInfo(SendMessage webCallMessage) throws DaoException;

    /**
     * 查询数据库
     * @param updateTime
     * @return
     * @throws DaoException
     */
    List<SendMessage> queryMessage(@Param("updateTime") String updateTime) throws DaoException;

    /**
     * 更新通话结果
     * @param webCallMessage
     * @return
     * @throws DaoException
     */
    Integer updateMessage(List<SendMessage> webCallMessage) throws DaoException;
}
