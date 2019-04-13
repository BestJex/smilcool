package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.ResourceQueryForm;
import com.smilcool.server.core.pojo.vo.ResourceVO;
import com.smilcool.server.core.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Angus
 * @date 2019/3/20
 */
@Api(tags = "2.1", description = "资源接口")
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation("资源列表")
    @GetMapping("/resource")
    public Result<List<ResourceVO>> getResourceList(ResourceQueryForm form) {
        List<ResourceVO> resourceList = resourceService.getResourceList(form);
        return Result.success(resourceList);
    }

    @ApiOperation("资源信息")
    @GetMapping("/resource/{id}")
    public Result<ResourceVO> getResource(@PathVariable Integer id) {
        ResourceVO resource = resourceService.getResource(id);
        return Result.success(resource);
    }
}
