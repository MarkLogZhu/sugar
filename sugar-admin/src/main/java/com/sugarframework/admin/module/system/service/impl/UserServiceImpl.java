package com.sugarframework.admin.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sugarframework.admin.module.common.entity.SysUser;
import com.sugarframework.admin.module.common.enums.UserStatusEnum;
import com.sugarframework.admin.module.common.mapper.SysUserMapper;
import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.common.model.UserQueryDto;
import com.sugarframework.admin.module.system.service.UserService;
import com.sugarframework.core.exception.BusinessException;
import com.sugarframework.core.model.PageDto;
import com.sugarframework.core.model.PageResponseDto;
import com.sugarframework.core.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhu
 * @description: 用户服务实现类
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

    @Override
    public PageResponseDto fetchList(UserQueryDto queryDto, PageDto pageDto) {
        QueryWrapper queryWrapper = getQueryWrapper(queryDto);
        IPage iPage = sysUserMapper.selectPage(pageDto.getPage(), queryWrapper);
        return PageResponseDto.success(iPage.getTotal(), iPage.getCurrent(), assemblyUserDtoList(iPage.getRecords()));
    }

    /**
     * 组装 DTO 对象
     *
     * @param userList
     * @return
     */
    private List<Object> assemblyUserDtoList(List<SysUser> userList) {
        List<Object> resultList = new ArrayList<>();
        userList.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(user, userDto);
                    return userDto;
                })
                .forEach(user -> resultList.add(user));
        return resultList;
    }


    /**
     * 获取查询对象
     *
     * @param queryDto
     * @return
     */
    private QueryWrapper getQueryWrapper(UserQueryDto queryDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(queryDto.getAccount())) {
            queryWrapper.like("account", queryDto.getAccount());
        }
        if (StringUtils.isNotEmpty(queryDto.getUserName())) {
            queryWrapper.like("user_name", queryDto.getUserName());
        }
        if (queryDto.getStatus() != null) {
            queryWrapper.eq("status", queryDto.getStatus());
        }
        return queryWrapper;
    }
}
