package com.sugarframework.admin.module.system.service;

import com.sugarframework.admin.module.common.model.UserDto;

/**
 * @author zhu
 * @description: 登录接口
 * @date 2019-06-20 15:13
 */
public interface SysLoginService {

    /**
     * 表单登录
     * @param loginUser
     * @return
     */
    UserDto loginViaForm(UserDto loginUser);


    void logout();
}
