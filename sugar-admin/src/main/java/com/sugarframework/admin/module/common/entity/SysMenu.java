package com.sugarframework.admin.module.common.entity;

import lombok.Data;

import java.util.Date;

/**
  * @description: 菜单实体类
  * @author zhu
  * @date 2019-06-24 16:51
  */
@Data
public class SysMenu {

    /** 菜单ID */
    private Long menuId;
    /** 菜单名称 */
    private String menuName;
    /** 权限字符串 */
    private String menuCode;
    /** 父菜单ID */
    private Long parentId;
    /** 显示顺序 */
    private Integer displayOrder;
    /** 菜单URL */
    private String url;
    /** 类型:0目录,1菜单,2按钮 */
    private String menuType;
    /** 菜单状态:0显示,1隐藏 */
    private String visible;
    /** 菜单图标 */
    private String icon;
    /** 创建人 */
    private String creator;
    /**创建时间**/
    private Date createTime;
    /**修改人**/
    private String modifier;
    /**修改时间**/
    private String modifyTime;
    /**是否删除标记**/
    private Boolean delFlag;
    /**备注**/
    private  String remark;



}
