package com.ccic.service.wencallconfig;

import com.ccic.entity.webcallconfig.TemplateConfig;
import com.github.pagehelper.PageInfo;

/**
 * Created by TaoHang on 2019/8/16.
 */
public interface TemplateConfigService {
    /**
     * 新增模板配置信息
     *
     * @param templateConfig
     * @return
     */
    boolean addTemplateConfig(TemplateConfig templateConfig);

    /**
     * 删除模板配置信息
     *
     * @param templateConfig
     * @return
     */
    boolean deleteTemplateConfig(TemplateConfig templateConfig);

    /**
     * 修改模板配置信息
     *
     * @param templateConfig
     * @return
     */
    boolean updateTemplateConfig(TemplateConfig templateConfig);

    /**
     * 查询已配置模板信息
     * @param templateConfig
     * @return
     */
    PageInfo<TemplateConfig> queryTemplateConfig(TemplateConfig templateConfig);

}
