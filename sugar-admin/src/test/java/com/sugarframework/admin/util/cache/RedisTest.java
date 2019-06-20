package com.sugarframework.admin.util.cache;

import com.sugarframework.admin.module.common.entity.SysUser;
import com.sugarframework.admin.config.cache.RedisUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhu
 * @description: Redis 测试
 * @date 2019-06-20 15:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testSimple() {
        String userKey = "ADMIN";
        if(!redisUtils.hasKey(userKey)){
            SysUser user = new SysUser();
            user.setUserId(new Long(10000));
            user.setAccount("admin");
            user.setPassword("admin");
            // 存数据
            redisUtils.set(userKey, user);
        }
        Assert.assertNotNull(redisUtils.get(userKey));
        Assert.assertEquals(-1,redisUtils.getExpire(userKey));
        Assert.assertTrue(redisUtils.expire(userKey, 1000));
        redisUtils.del(userKey);
        Assert.assertNull(redisUtils.get(userKey));
    }

}
