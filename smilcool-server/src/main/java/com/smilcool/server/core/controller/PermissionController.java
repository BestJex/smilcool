package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.common.util.BindingResultUtil;
import com.smilcool.server.core.pojo.form.PermissionAddForm;
import com.smilcool.server.core.pojo.vo.PermissionVO;
import com.smilcool.server.core.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/3/29
 */
@Api(description = "权限接口", tags = {"1.3"})
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("权限列表")
    @GetMapping("/permission")
    public Result<List<PermissionVO>> list() {
        List<PermissionVO> permissionList = permissionService.list();
        return Result.success(permissionList);
    }

    @ApiOperation("权限添加")
    @PostMapping("/permission")
    public Result<PermissionVO> add(@RequestBody @Valid PermissionAddForm permissionAddForm, BindingResult bindingResult) {
        BindingResultUtil.validate(bindingResult);
        PermissionVO permission = permissionService.add(permissionAddForm);
        return Result.success(permission);
    }
}
