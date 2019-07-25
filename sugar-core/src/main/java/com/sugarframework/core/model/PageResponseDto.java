package com.sugarframework.core.model;

import com.sugarframework.core.enums.ResultCode;
import lombok.Data;

/**
 * @author zhu
 * @description: 分页请求响应结果对象
 * @date 2019-07-25 14:03
 */
@Data
public class PageResponseDto extends ResponseDto {

    /**
     * 总条数
     */
    private Long total;
    /**
     * 当前页数
     */
    private Long pageNum;


    protected PageResponseDto(ResultCode resultCode, Long total, Long pageNum, Object data) {
        super(resultCode, data);
        this.total = total;
        this.pageNum = pageNum;
    }


    public static PageResponseDto success(Long total, Long pageNum, Object data) {
        return new PageResponseDto(ResultCode.SUCCESS, total, pageNum, data);
    }

}
