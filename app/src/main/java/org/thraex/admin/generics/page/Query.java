package org.thraex.admin.generics.page;

import java.io.Serializable;

/**
 * @author 鬼王
 * @date 2022/03/14 18:37
 */
public class Query implements Serializable {

    private int page;

    private int size;

    public Query() {
        this.page = 1;
        this.size = 10;
    }

    public int getPage() {
        return page;
    }

    public Query setPage(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Query setSize(int size) {
        this.size = size;
        return this;
    }

}
