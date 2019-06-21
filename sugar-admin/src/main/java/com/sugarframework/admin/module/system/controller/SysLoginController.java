package com.sugarframework.admin.module.system.controller;

import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.common.validation.group.UserValidationGroup;
import com.sugarframework.admin.module.system.service.SysLoginService;
import com.sugarframework.core.base.controller.BaseController;
import com.sugarframework.core.model.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhu
 * @description: 登陆控制器
 * @date 2019-06-14 13:09
 */
@Controller
public class SysLoginController extends BaseController {

    @Autowired
    private SysLoginService sysLoginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseDto login(@Validated({UserValidationGroup.Login.class}) UserDto loginUser) {
        sysLoginService.loginViaForm(loginUser);
        return ResponseDto.success();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseDto logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sysLoginService.logout();
        if (isAccessPage(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        return ResponseDto.success();
    }

}
