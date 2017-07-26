package com.andy.rendercontroller;

/**
 * Created by Andy on 2017/7/26.
 */

public interface DataLoader<T> {
    /**
     * 加载数据. 数据可能来自本地或者远程
     * @return 加载的数据
     */
    T loadData();
}
