package com.sugarframework.admin.module.common.enums;

/**
 * @author zhu
 * @description: 用户状态枚举类
 * @date 2019-06-20 14:57
 */
public enum UserStatusEnum {

    ENABLE(1, "启用"),
    FREEZE(2, "冻结"),
    DELETE(3, "删除"),;

    private int code;
    private String description;

    UserStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
