package com.sugarframework.admin.module.common.model;

import com.sugarframework.admin.module.common.validation.group.UserValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
  * @description: 用户 Dto 对象
  * @author zhu
  * @date 2019-06-20 8:57
  */
@Data
public class UserDto {

    private Long userId;
    @NotEmpty(message = "账号不能为空" ,groups = {UserValidationGroup.class,UserValidationGroup.Login.class})
    private String account;
    @NotEmpty(message = "密码不能为空",groups ={UserValidationGroup.Login.class})
    private String password;
    private String userName;
    private Date birthday;
    private String email;
    private String phone;
    private Integer status;
    private String creator;
    private Date createTime;
    private String modifier;
    private Date modifyTime;
}
