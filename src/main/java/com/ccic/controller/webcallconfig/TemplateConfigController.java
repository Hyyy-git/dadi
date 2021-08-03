package com.ccic.controller.webcallconfig;

import com.ccic.config.domain.JSONResult;
import com.ccic.config.domain.PageResult;
import com.ccic.entity.webcallconfig.TemplateConfig;
import com.ccic.service.wencallconfig.TemplateConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TaoHang on 2019/8/15.
 */
@RestController
@RequestMapping("/webCallConfig")
@CrossOrigin
public class TemplateConfigController {

    @Autowired
    private TemplateConfigService templateConfigService;

    @RequestMapping(value = "/addTemplate")
    public JSONResult addTemplate(@RequestBody TemplateConfig templateConfig) {
        try {
            templateConfigService.addTemplateConfig(templateConfig);
            return JSONResult.success("success");
        } catch (Exception e) {
            return JSONResult.error("告警模板配置信息新增错误：", e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteTemplate")
    public JSONResult deleteTemplate(@RequestBody TemplateConfig templateConfig) {
        try {
            templateConfigService.deleteTemplateConfig(templateConfig);
            return JSONResult.success("success");
        } catch (Exception e) {
            return JSONResult.error("告警模板配置信息刪除错误：", e.getMessage());
        }
    }

    @RequestMapping(value = "/updateTemplate")
    public JSONResult updateTemplate(@RequestBody TemplateConfig templateConfig) {
        try {
            templateConfigService.updateTemplateConfig(templateConfig);
            return JSONResult.success("success");
        } catch (Exception e) {
            return JSONResult.error("告警模板配置信息修改错误：", e.getMessage());
        }
    }

    @RequestMapping(value = "/queryTemplate")
    public JSONResult queryTemplate(@RequestBody TemplateConfig templateConfig) {
        try {
            PageInfo<TemplateConfig> templateConfigList = templateConfigService.queryTemplateConfig(templateConfig);
            PageResult result = new PageResult(templateConfigList.getTotal(), templateConfigList.getList());
            return JSONResult.success("success", result);
        } catch (Exception e) {
            return JSONResult.error("告警模板配置信息修改错误：", e.getMessage());
        }
    }

}
