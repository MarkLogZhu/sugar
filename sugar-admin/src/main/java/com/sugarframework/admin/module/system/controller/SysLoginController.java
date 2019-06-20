package com.sugarframework.admin.module.system.controller;

import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.common.validation.group.UserValidationGroup;
import com.sugarframework.core.base.controller.BaseController;
import com.sugarframework.core.model.ResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhu
 * @description: 登陆控制器
 * @date 2019-06-14 13:09
 */
@Controller
public class SysLoginController extends BaseController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseDto login(@Validated({UserValidationGroup.Login.class}) UserDto userDto) {

        return ResponseDto.success();
    }


}
