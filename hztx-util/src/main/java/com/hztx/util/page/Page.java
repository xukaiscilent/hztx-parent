package com.hztx.util.page;

import java.util.List;

/**
 * Created by InThEnd on 2016/3/24.
 * 页内容
 */
public interface Page<T> {

    /**
     * 当前页码
     */
    int getNumber();

    /**
     * 页大小
     */
    int getSize();

    /**
     * 总页数
     */
    int getTotalPages();

    /**
     * 总记录条数
     */
    int getTotalElements();


    /**
     * 获取内容
     */
    List<T> getContent();

    /**
     * 是否有内容
     */
    boolean hasContent();

    /**
     * 下一页
     */
    Pageable nextPageable();

    /**
     * 前一页
     */
    Pageable previousPageable();

    boolean isLastPageNumber();

}
