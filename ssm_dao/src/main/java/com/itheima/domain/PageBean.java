package com.itheima.domain;

import java.util.List;

public class PageBean {
    //必须条件
    private Integer pageNum;
    private Integer pageSize;
    //需要后台查询的
    private Integer totalCount;
    private List<Product> list;
    //可求出的数据
    private Integer totalPage;
    private Integer prePage;
    private Integer nextPage;
    private Integer beginIndex;
    private Integer endIndex;

    public PageBean(Integer pageNum, Integer pageSize, Integer totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        this.prePage = pageNum == 1 ? 1 : pageNum - 1;
        this.nextPage = pageNum == totalPage ? totalPage : pageNum + 1;
        this.beginIndex = (pageNum - 1) * pageSize + 1;
        this.endIndex = pageNum * pageSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", list=" + list +
                ", totalPage=" + totalPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", beginIndex=" + beginIndex +
                ", endIndex=" + endIndex +
                '}';
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List getList() {
        return list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }
}
