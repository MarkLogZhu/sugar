package com.sugarframework.core.util.seed;

import com.sugarframework.core.enums.ResultCode;
import com.sugarframework.core.exception.BusinessException;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author zhu
 * @description: 种子工具类
 * @date 2019-06-21 9:47
 */
public class SeedUtils {


    /**
     * 获取登录令牌
     * @return
     */
    public static String getLoginToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "") + "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(ResultCode.ERROR_SEED);
        }
    }


}
