package com.sugarframework.admin.module.system.controller;

import com.sugarframework.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
  * @description: 登陆控制器
  * @author zhu
  * @date 2019-06-14 13:09
  */
@Controller
public class SysLoginController extends BaseController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }




}
