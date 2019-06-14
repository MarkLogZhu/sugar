package com.sugarframework.admin.module.system.controller;

import com.sugarframework.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
  * @description: 首页控制器
  * @author zhu
  * @date 2019-06-14 13:13
  */
@Controller
public class SysHomeController extends BaseController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
