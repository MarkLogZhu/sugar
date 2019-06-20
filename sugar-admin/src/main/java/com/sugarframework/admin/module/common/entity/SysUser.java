package com.sugarframework.admin.module.common.entity;

import lombok.Data;

import java.util.Date;

/**
  * @description: 用户实体类
  * @author zhu
  * @date 2019-06-20 14:51
  */
@Data
public class SysUser {
    private Long userId;
    private String account;
    private String password;
    private String salt;
    private String userName;
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private Integer status;
    private String creator;
    private Date createTime;
    private String modifier;
    private Date modifyTime;
}
