package com.sugarframework.admin.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugarframework.admin.module.common.entity.SysMenu;
import com.sugarframework.admin.module.common.mapper.SysMenuMapper;
import com.sugarframework.admin.module.common.model.MenuDto;
import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.admin.module.system.service.SysMenuManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @description: 系统菜单服务接口实现
 * @date 2019-06-24 16:18
 */
@Service
public class SysMenuManagementServiceImpl implements SysMenuManagementService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    private static final Long TOP_PARENT_ID= new Long(0);

    @Override
    public List<MenuDto> getMenuList(UserDto userDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag", Boolean.FALSE);
        queryWrapper.in("menu_type", Arrays.asList(new String[]{"M", "C"}));
        List<SysMenu> menuList = sysMenuMapper.selectList(queryWrapper);
        return getMenuTreeList(menuList, TOP_PARENT_ID);
    }


    /**
     * 获取菜单树列表
     *
     * @param menuList
     * @param pid
     * @return
     */
    private List<MenuDto> getMenuTreeList(List<SysMenu> menuList, Long pid) {
        // 查找所有菜单
        List<MenuDto> childrenList = new ArrayList<>();
        menuList.stream()
                .map(menu -> MenuDto.assemblyMenuDto(menu))
                .filter(d -> Objects.equals(pid, d.getParentId()))
                .collect(Collectors.toList())
                .forEach(d -> {
                    d.setChildren(getMenuTreeList(menuList, d.getMenuId()));
                    childrenList.add(d);
                });
        return childrenList;
    }
}
