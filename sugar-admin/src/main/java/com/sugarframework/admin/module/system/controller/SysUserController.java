package com.sugarframework.admin.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sugarframework.admin.module.common.entity.SysUser;
import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.common.model.UserQueryDto;
import com.sugarframework.admin.module.system.service.UserService;
import com.sugarframework.core.base.controller.BaseController;
import com.sugarframework.core.model.PageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhu
 * @description: 用户控制器
 * @date 2019-07-23 9:14
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/managementPage")
    public String managementPage() {
        return "/system/user/management";
    }

    @GetMapping("/fetchList")
    @ResponseBody
    public PageResponseDto fetchList(UserQueryDto queryDto) {
        return userService.fetchList(queryDto, getPage());
    }
}
