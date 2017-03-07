package com.andy.infrastructure.interfaces;

import com.andy.infrastructure.bean.MeiziData;

import java.util.List;

/**
 * Created by Andy on 2017/3/7.
 */

public interface IMeiziFragment extends IBaseFragment {
    void updateMeiziData(List<MeiziData> list);
}
