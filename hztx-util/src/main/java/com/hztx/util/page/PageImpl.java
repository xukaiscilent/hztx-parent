package com.hztx.util.page;


import com.google.gson.annotations.Expose;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by InThEnd on 2016/3/24.
 * <p/>
 * 分页实现。
 */

@Api("分页信息")
public class PageImpl<T> implements Page<T> {
    public String toString(){
        return "分页页码:"+number+" "+"分页大小:"+size+" "+"有效数据总条数:"+totalElements+" "+"总页数:"+totalPages+" "+"数据主体:"+content;
    }

    @Expose
    @ApiModelProperty("分页页码")
    private int number;

    @Expose
    @ApiModelProperty("分页大小")
    private int size;

    @Expose
    @ApiModelProperty("有效数据总条数")
    private int totalElements;

    @Expose
    @ApiModelProperty("总页数")
    private int totalPages;

    @Expose
    @ApiModelProperty("数据主体")
    private List<T> content;

    @ApiModelProperty("")
    private transient Pageable pageable;

    public PageImpl(Pageable pageable, int totalElements, List<T> content) {
        number = pageable == null ? -1 : pageable.getPageNumber();
        size = pageable == null ? -1 : pageable.getPageSize();
        this.totalElements = totalElements;
        this.totalPages = pageable == null ? 1 : (totalElements + size - 1) / size;
        this.content = content;
        this.pageable = pageable;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public int getTotalElements() {
        return totalElements;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return content != null && content.size() > 0;
    }

    @Override
    public Pageable nextPageable() {
        return pageable.next();
    }

    @Override
    public Pageable previousPageable() {
        return pageable.previousOrFirst();
    }

    @Override
    public boolean isLastPageNumber() {
        return pageable.getPageNumber()==totalElements/pageable.getPageSize()+1;
    }
}
