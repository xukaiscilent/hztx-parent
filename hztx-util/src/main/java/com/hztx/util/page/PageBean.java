/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.hztx.util.page;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 分页辅助类
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月16日 下午4:34:12
 */
@Component("pageBean")
public class PageBean {

    // 当前页列表数据
    private List<Object> list;

    // 符合条件的记录总数
    private int rows;

    // 每页显示多少条
    private int pageSize = FIFTEEN;

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    // 当前页码
    private int pageNo = 1;

    // 数字 15
    private static final int FIFTEEN = 15;

    public PageBean() {
        this.pageSize = PageRowsUtil.getPageRows();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getStartRowNum() {
        return (pageNo - 1) * pageSize;
    }

    public int getEndRowNum() {
        return pageSize;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Object> getList() {
        return list;
    }

    public int getRows() {
        return rows;
    }
}
