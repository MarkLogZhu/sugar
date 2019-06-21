package com.sugarframework.admin.module.system.service;

import com.sugarframework.admin.module.common.entity.SysUser;

/**
 * @author zhu
 * @description: 用户服务接口
 * @date 2019-06-20 14:13
 */
public interface UserService {
    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);


}
