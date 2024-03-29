package com.whx.creationhsin.domain;

import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
    //当前页
    private long total;
    //当前页记录
    private List rows;
    //增加第二列
    private List row2;

    public PageBean(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
