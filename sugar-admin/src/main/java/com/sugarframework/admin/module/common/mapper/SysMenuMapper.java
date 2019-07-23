package com.sugarframework.admin.module.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sugarframework.admin.module.common.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
  * @description: 菜单 DAO 接口
  * @author zhu
  * @date 2019-06-20 14:56
  */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}
