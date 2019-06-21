package com.sugarframework.admin.module.system.service.impl;

import com.sugarframework.admin.config.cache.RedisUtils;
import com.sugarframework.admin.module.common.entity.SysUser;
import com.sugarframework.admin.module.common.enums.UserStatusEnum;
import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.system.service.SysLoginService;
import com.sugarframework.admin.module.system.service.UserService;
import com.sugarframework.core.constant.cache.UserCacheConstant;
import com.sugarframework.core.enums.ResultCode;
import com.sugarframework.core.exception.BusinessException;
import com.sugarframework.core.util.StringUtils;
import com.sugarframework.core.util.bean.BeanUtils;
import com.sugarframework.core.util.encrypted.MD5Utils;
import com.sugarframework.core.util.seed.SeedUtils;
import com.sugarframework.core.util.web.CookieUtils;
import com.sugarframework.core.util.web.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhu
 * @description: 登录接口实现类
 * @date 2019-06-20 15:13
 */
@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserDto loginViaForm(UserDto loginUser) {
        UserDto currentUserDto = validateUser(loginUser);
        String loginToken = SeedUtils.getLoginToken();
        // 客户端返回 cookie
        CookieUtils.writeLoginToken(ServletUtils.getResponse(), loginToken);
        // 服务端写入 redis
        if (!redisUtils.set(loginToken, currentUserDto, UserCacheConstant.LONGIN_SESSION_EXPIRED_IME)) {
            throw new BusinessException(ResultCode.ERROR_CACHE);
        }
        return currentUserDto;
    }

    @Override
    public void logout() {
        HttpServletRequest request = ServletUtils.getRequest();
        // 获取登录 token
        String loginToken = CookieUtils.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            // 删除客户端 cookie
            CookieUtils.delLoginToken(request, ServletUtils.getResponse());
            // redis 删除信息
            redisUtils.del(loginToken);
        }
    }

    /**
     * 检验用户信息
     *
     * @param loginUser 用户登录信息
     * @return 当前登录用户所有信息
     */
    private UserDto validateUser(UserDto loginUser) {
        // 1.验证账号是否存在
        SysUser sysUser = userService.findUserByAccount(loginUser.getAccount());
        if (sysUser == null) {
            throw new BusinessException(ResultCode.ACCOUNT_NOT_EXIST);
        }
        // 2.验证密码是否正确
        String encryptedPassword = MD5Utils.encryptPassword(loginUser.getPassword(), sysUser.getSalt());
        if (!encryptedPassword.equals(sysUser.getPassword())) {
            throw new BusinessException(ResultCode.PASSWORD_WRONG);
        }
        // 3.验证账号状态
        if (UserStatusEnum.FREEZE.getCode() == sysUser.getStatus()) {
            throw new BusinessException(ResultCode.ACCOUNT_STATUS_FREEZE);
        }
        // 4.将敏感信息屏蔽
        sysUser.setPassword(null);
        // 5.转为 DTO 返回
        UserDto userDto = new UserDto();
        BeanUtils.copyBeanProp(sysUser, userDto);
        return userDto;
    }
}
