package com.hztx.util.page;


import org.springframework.util.Assert;

/**
 * Created by Administrator on 2016/3/24.
 */
public class Pageable {

    private int pageNumber;
    private int pageSize;

    private int index;
    private int length;

    public Pageable(int pageNumber, int pageSize) {
        Assert.isTrue(pageNumber > 0, "页数必须大于0.");
        Assert.isTrue(pageSize >= 0, "页大小必须大于等于0.");
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

        this.index = (pageNumber - 1) * pageSize;
        this.length = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getLength() {
        return length;
    }

    public int getIndex() {
        return index;
    }

    public Pageable next() {
        return new Pageable(pageNumber + 1, pageSize);
    }

    public Pageable previousOrFirst() {
        if (pageNumber == 1) {
            return this;
        }
        return new Pageable(pageNumber - 1, pageSize);
    }

    public Pageable first() {
        return new Pageable(1, pageSize);
    }





}
