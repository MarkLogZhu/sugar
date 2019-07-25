package com.sugarframework.admin.module.common.model;

import lombok.Data;

/**
 * @author zhu
 * @description: 用户查询条件 DTO
 * @date 2019-07-25 9:17
 */
@Data
public class UserQueryDto {

    private String account;
    private String userName;
    private Integer status;


}
