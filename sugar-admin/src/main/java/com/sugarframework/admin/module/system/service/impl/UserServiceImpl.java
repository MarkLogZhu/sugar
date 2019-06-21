package com.sugarframework.admin.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugarframework.admin.module.common.entity.SysUser;
import com.sugarframework.admin.module.common.enums.UserStatusEnum;
import com.sugarframework.admin.module.common.mapper.SysUserMapper;
import com.sugarframework.admin.module.system.service.UserService;
import com.sugarframework.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
  * @description: 用户服务实现类
  * @author zhu
  * @date 2019-06-20 14:55
  */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserByAccount(String account) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        queryWrapper.ne("status", UserStatusEnum.DELETE.getCode());
        List<SysUser> userList = sysUserMapper.selectList(queryWrapper);
        if (userList.size() == 0) {
            throw new BusinessException("账号不存在");
        }
        if (userList.size() > 1) {
            throw new BusinessException("账号异常，存在多用户情况");
        }
        return userList.get(0);
    }
}
