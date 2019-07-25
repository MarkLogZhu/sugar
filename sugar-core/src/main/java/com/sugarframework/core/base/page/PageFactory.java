package com.sugarframework.core.base.page;

import com.sugarframework.core.model.PageDto;
import com.sugarframework.core.util.web.ServletUtils;

/**
 * @author zhu
 * @description: 分页工厂
 * @date 2019-07-25 10:05
 */
public class PageFactory {

    public static final Integer PAGE_NUM_VALUE = 1;
    public static final Integer PAGE_SIZE_VALUE = 20;


    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";
    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    public static PageDto buildPageRequest() {
        return getRequestPage();
    }

    /**
     * 获取默认分页对象
     *
     * @return
     */
    public static PageDto defaultPage() {
        PageDto pageDto = new PageDto();
        pageDto.setPageNum(PAGE_NUM_VALUE);
        pageDto.setPageSize(PAGE_SIZE_VALUE);
        return pageDto;
    }

    /**
     * 从请求中获取分页对象
     *
     * @return
     */
    public static PageDto getRequestPage() {
        Integer pageNum = ServletUtils.getParameterToInt(PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(PAGE_SIZE);
        if (pageNum != null && pageSize != null) {
            PageDto pageDto = new PageDto();
            pageDto.setPageNum(pageNum);
            pageDto.setPageSize(pageSize);
            pageDto.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
            pageDto.setIsAsc(ServletUtils.getParameter(IS_ASC));
            return pageDto;
        }
        return defaultPage();
    }

}
