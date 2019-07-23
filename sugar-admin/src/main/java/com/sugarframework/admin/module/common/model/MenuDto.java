package com.sugarframework.admin.module.common.model;

import com.sugarframework.admin.module.common.entity.SysMenu;
import com.sugarframework.core.util.bean.BeanUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhu
 * @description: 菜单
 * @date 2019-06-24 16:08
 */
@Data
public class MenuDto extends SysMenu{

    /**
     * 子菜单
     */
    private List<MenuDto> children = new ArrayList<>();


    public static MenuDto assemblyMenuDto(SysMenu sysMenu) {
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyBeanProp(sysMenu, menuDto);
        return menuDto;
    }
}
