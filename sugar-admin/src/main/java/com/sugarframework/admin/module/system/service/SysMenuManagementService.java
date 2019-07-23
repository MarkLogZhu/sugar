package com.sugarframework.admin.module.system.service;

import com.sugarframework.admin.module.common.entity.SysMenu;
import com.sugarframework.admin.module.common.model.MenuDto;
import com.sugarframework.admin.module.common.model.UserDto;

import java.util.List;

/**
 * @author zhu
 * @description: 系统菜单服务接口
 * @date 2019-06-24 16:17
 */
public interface SysMenuManagementService {

    /**
     * 根据用户获取菜单列表
     * @param userDto
     * @return
     */
    List<MenuDto> getMenuList(UserDto userDto);


}
