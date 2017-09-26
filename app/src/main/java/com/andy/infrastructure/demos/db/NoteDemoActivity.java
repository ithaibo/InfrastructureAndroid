package com.andy.infrastructure.demos.db;

import android.util.Log;
import android.view.View;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.infrastructure.MyApp;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.DbActNoteBinding;
import com.andy.infrastructure.demos.db.note.User;
import com.andy.infrastructure.demos.db.note.UserDao;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Andy on 2017/5/16.
 */

public class NoteDemoActivity extends DataBindActivity<DbActNoteBinding> {

    private UserDao userDao;

    @Override
    protected int getLayoutId() {
        return R.layout.db_act_note;
    }

    @Override
    protected void initData() {
        userDao = ((MyApp) getApplication()).getDaoSession()
                .getUserDao();

        loadAll();
    }

    private void insertData() {
        Log.i("Dao", "insert data");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 25);
        for (int i = 0; i < 10; i++) {
            User item = new User();
            item.setName("user" + i);
            item.setTime(calendar.getTimeInMillis());
            userDao.insert(item);
        }
    }

    private void loadAll() {
        List<User> queryList = userDao.queryBuilder().list();
        Log.i("Dao", "data total: " + queryList.toString());
        mDataBind.tvCount.setText(queryList.toString());
    }

    @Override
    protected void initViews() {


        mDataBind.btnDeleteOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                mDataBind.tvCount.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadAllData();
                    }
                }, 1000);

            }
        });

        mDataBind.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                mDataBind.tvCount.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadAllData();
                    }
                }, 1000);
            }
        });
    }

    private void deleteData() {
        Log.i("Dao", "delete data");

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        userDao.rx()
                .loadAll()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<List<User>, List<Long>>() {
                    @Override
                    public List<Long> call(List<User> users) {
                        List<Long> result = new LinkedList<Long>();
                        for (User user: users) {
                            if (user.getTime() < calendar.getTimeInMillis()) {
                                result.add(user.getId());
                            }
                        }
                        return result;
                    }
                })
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        for (Long id : longs) {
                            userDao.deleteByKey(id);
                        }
                    }
                });
        mDataBind.tvCount.setText("");

    }

    private void loadAllData() {
        Log.i("Dao", "load all");
        userDao.rx()
                .loadAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        mDataBind.tvCount.setText(users.toString());
                    }
                });

    }
}
