package com.ccic.dao.webcallconfig;

import com.ccic.config.exception.DaoException;
import com.ccic.entity.webcallconfig.TemplateConfig;

import java.util.List;

/**
 * Created by acer on 2019/8/19.
 */
public interface TemplateConfigDao {

    /**
     * 获取数据路序列
     *
     * @return
     * @throws DaoException
     */
    String getSerialNo() throws DaoException;

    /**
     * 新增模板配置
     *
     * @param templateConfig
     * @return
     * @throws DaoException
     */
    Integer addTemplateConfig(TemplateConfig templateConfig) throws DaoException;

    /**
     * 删除模板配置 （更新state状态：Y = 有效； N = 无效）
     *
     * @param templateConfig
     * @return
     * @throws DaoException
     */
    Integer deleteTemplateConfig(TemplateConfig templateConfig) throws DaoException;

    /**
     * 修改模板配置
     *
     * @param templateConfig
     * @return
     * @throws DaoException
     */
    Integer updateTemplateConfig(TemplateConfig templateConfig) throws DaoException;


    /**
     * 查询模板配置
     *
     * @param templateConfig
     * @return
     * @throws DaoException
     */
    List<TemplateConfig> queryTemplateConfig(TemplateConfig templateConfig) throws DaoException;

}
