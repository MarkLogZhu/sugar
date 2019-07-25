package com.sugarframework.admin.module.system.service;

import com.sugarframework.admin.module.common.entity.SysUser;
import com.sugarframework.admin.module.common.model.UserQueryDto;
import com.sugarframework.core.model.PageDto;
import com.sugarframework.core.model.PageResponseDto;

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

    /**
     * 获取用户列表
     * @param queryDto  查询条件
     * @param pageDto   分页对象
     * @return
     */
    PageResponseDto fetchList(UserQueryDto queryDto, PageDto pageDto);
}
