package com.ccic.service.wencallconfig.impl;

import com.ccic.config.exception.DaoException;
import com.ccic.config.exception.TransactionRuntimeException;
import com.ccic.config.pub.MyConst;
import com.ccic.dao.webcallconfig.TemplateConfigDao;
import com.ccic.entity.webcallconfig.TemplateConfig;
import com.ccic.service.BaseService;
import com.ccic.service.wencallconfig.TemplateConfigService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by TaoHang on 2019/8/16.
 */
@Service
public class TemplateConfigServiceImpl extends BaseService implements TemplateConfigService {

    @Autowired
    private TemplateConfigDao templateConfigDao;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional
    public boolean addTemplateConfig(TemplateConfig templateConfig) {
        int iRet = 0;
        try {
            log.info("告警消息模板配置新增 ：" + templateConfig.toString());
            String serialNo = getSerialNo();
            templateConfig.setId(serialNo);
            templateConfig.setState(MyConst.TEMPLATE_CONFIG_STATE_YES.getValue());
            templateConfig.setInputTime(df.format(new Date()));
            templateConfig.setUpdateTime(df.format(new Date()));
         //   templateConfig.setTemplateId(String.valueOf(System.currentTimeMillis()));
            templateConfig.setSimilarity(2);
            templateConfig.setTimeOut("1");
            templateConfig.setUpdateUserId("system");
            templateConfig.setTimeStamp("Y");
            templateConfig.setMailType("1");
            iRet = templateConfigDao.addTemplateConfig(templateConfig);
            if (iRet != 1) {
                throw new DaoException("告警消息模板配置新增错误！");
            }
        } catch (Exception e) {
            log.error("告警消息模板配置新增失败:", e);
            throw new TransactionRuntimeException("告警消息模板配置新增失败:", e);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteTemplateConfig(TemplateConfig templateConfig) {
        int iRet = 0;

        try {
            log.info("告警消息模板配置删除 ：" + templateConfig.toString());
            templateConfig.setState(MyConst.TEMPLATE_CONFIG_STATE_NO.getValue());
            iRet = templateConfigDao.deleteTemplateConfig(templateConfig);
            if (iRet != 1) {
                throw new DaoException("告警消息模板配置删除错误！");
            }
        } catch (Exception e) {
            log.error("告警消息模板配置删除失败:", e);
            throw new TransactionRuntimeException("告警消息模板配置删除失败:", e);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateTemplateConfig(TemplateConfig templateConfig) {
        int iRet = 0;
        try {
            log.info("告警消息模板配置修改 ：" + templateConfig.toString());
            templateConfig.setUpdateTime(df.format(new Date()));
            iRet = templateConfigDao.updateTemplateConfig(templateConfig);
            if (iRet != 1) {
                throw new DaoException("告警消息模板配置删除错误！");
            }
        } catch (Exception e) {
            log.error("告警消息模板配置修改失败:", e);
            throw new TransactionRuntimeException("告警消息模板配置修改失败:", e);
        }
        return true;
    }

    @Override
    public PageInfo<TemplateConfig> queryTemplateConfig(TemplateConfig templateConfig) {
        try {
            log.info("告警消息模板配置查询 ：" + templateConfig.toString());
          //  PageHelper.startPage(templateConfig.getPageNum(), templateConfig.getPageSize());
            PageHelper.startPage(templateConfig.getPagenum(), templateConfig.getPagesize());
            Page<TemplateConfig> resultList = (Page<TemplateConfig>) templateConfigDao.queryTemplateConfig(templateConfig);
            return resultList.toPageInfo();
        } catch (Exception e) {
            log.error("告警消息模板配置查询失败:", e);
            throw new TransactionRuntimeException("告警消息模板配置查询失败:", e);
        }
    }

    /**
     * 获取数据库主键生成id
     *
     * @return
     * @throws DaoException
     */
    private String getSerialNo() throws DaoException {
        String serialNo = templateConfigDao.getSerialNo();
        return serialNo;
    }


}
