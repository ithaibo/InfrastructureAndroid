# MVP #
该Demo主要为：从服务器!()[gank.io](http://gank.io)上获取图片，加载到RecyclerView中。该流程由 **RxJava** + **Retrofit** 实现。

## 数据获取 ##
该流程由com.andy.infrastructure.demos.mvp.presenter.MeiziPresenterImpl.getMeiziData(int index)完成，其代码如下：
``` Java
    public void getMeiziData(int index) {
        Subscription subscription = GenServiceUtil
                .genInstance("http://gank.io")
                .createService(MeiziService.class)
                .getMeiziData(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeiziDataList>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(mContext, "加载完毕", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "出错了...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(MeiziDataList meiziData) {
                        meiziFragment.updateMeiziData(meiziData.getResults());
                    }
                });

        addSubscription(subscription);
    }
```
** 注 ** 上面的代码中的Retrofit的版本为2.2.0，该版本需要做以下步骤：
 - 添加依赖 ``` com.squareup.retrofit2:adapter-rxjava ```
 - 在Retrofit.Builder上添加代码 ``` addCallAdapterFactory(RxJavaCallAdapterFactory.create()) ```

这里面的MeiziService代码：
``` Java
public interface MeiziService {
    @GET("/api/data/福利/10/{page}")
    Observable<MeiziDataList> getMeiziData(@Path("page") int page);
}
```

## Item Bind ##
RecyclerView中Item进行Bind操作。在com.andy.infrastructure.holder.MeiziHolder.bind中使用Glide加载图片。

```
实现中...
```