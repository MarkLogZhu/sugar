package com.sugarframework.admin.module.system.controller;

import com.sugarframework.admin.module.common.holder.RequestContextHolder;
import com.sugarframework.admin.module.common.model.MenuDto;
import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.system.service.SysMenuManagementService;
import com.sugarframework.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhu
 * @description: 首页控制器
 * @date 2019-06-14 13:13
 */
@Controller
public class SysHomeController extends BaseController {

    @Autowired
    private SysMenuManagementService menuService;


    @GetMapping("/")
    public String index(Model model) {
        UserDto currentUser = RequestContextHolder.getCurrentUser();
        model.addAttribute("userName", currentUser.getUserName());
        model.addAttribute("menus", menuService.getMenuList(currentUser));
        return "index";
    }


}
