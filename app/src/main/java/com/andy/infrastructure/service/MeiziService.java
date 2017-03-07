package com.andy.infrastructure.service;

import com.andy.infrastructure.bean.BaseListBean;
import com.andy.infrastructure.bean.MeiziData;
import com.andy.infrastructure.bean.MeiziDataList;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Andy on 2017/3/7.
 */

public interface MeiziService {

    /**
     * 获取一组妹子图
     *
     * @param page
     * @return
     */
    @GET("/api/data/福利/10/{page}")
    Observable<MeiziDataList> getMeiziData(@Path("page") int page);

}
