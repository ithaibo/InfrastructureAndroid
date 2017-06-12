package com.andy.baselibrary.adapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andy on 2017/5/23.
 */

public interface AdapterDataManager<T extends Serializable> {
    void initDataSet(List<T> dataSet);
    void refreshData(List<T> dataSet);
    void appendData(List<T> dataSet);
    void clearData();
}
