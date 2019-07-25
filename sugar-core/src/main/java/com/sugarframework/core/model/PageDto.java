package com.sugarframework.core.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugarframework.core.util.StringUtils;
import lombok.Data;

/**
 * @author zhu
 * @description: 分页对象
 * @date 2019-07-25 9:58
 */
@Data
public class PageDto {
    /**
     * 当前记录页面
     */
    private Integer pageNum;
    /**
     * 每页显示记录数
     */
    private Integer pageSize;
    /**
     * 排序列
     */
    private String orderByColumn;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    private String isAsc;

    /**
     * 获取排序语句
     *
     * @return
     */
    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public IPage getPage() {
        return new Page(pageNum, pageSize);
    }
}
