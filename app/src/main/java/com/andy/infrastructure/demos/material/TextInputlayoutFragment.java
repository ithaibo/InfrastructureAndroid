package com.andy.infrastructure.demos.material;

import android.text.TextUtils;
import android.view.View;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.UserBean;
import com.andy.infrastructure.demos.material.FrgTextInputDaBind;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Andy on 2017/3/8.
 */

public class TextInputlayoutFragment extends DataBindFrgment<FrgTextInputDaBind> {

    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_material_text_input_layout;
    }

    @Override
    protected void initView(View rootView) {
        UserBean userBean = new UserBean();
        dataBinder.setUserData(userBean);
        initInputLayoutParams();
    }

    private void initInputLayoutParams() {
        dataBinder.inputLayoutUsername.setHint(getResources().getString(R.string.label_username));
        dataBinder.inputLayoutPassword.setHint(getResources().getString(R.string.label_password));

        Observable.create(new Observable.OnSubscribe<UserBean>() {
            @Override
            public void call(final Subscriber<? super UserBean> subscriber) {
                dataBinder.btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext(dataBinder.getUserData());
                    }
                });
            }
        }).filter(new Func1<UserBean, Boolean>() { //用户名是否合法
            @Override
            public Boolean call(UserBean userBean) {
                if(userBean==null ||
                        TextUtils.isEmpty(userBean.userName.get()))
                {
                    dataBinder.inputLayoutUsername.setError("Username cannot be null!");
                    return false;
                } else {
                    dataBinder.inputLayoutUsername.setError(null);
                    return true;
                }
            }
        }).filter(new Func1<UserBean, Boolean>() { //检查密码
            @Override
            public Boolean call(UserBean userBean) {
                if (userBean==null ||
                        userBean.password==null ||
                        TextUtils.isEmpty(userBean.password.get())||
                        "123456".equals(userBean.password.get())) {
                    dataBinder.inputLayoutPassword.setError("Password Wrong!");
                    return false;
                } else {
                    dataBinder.inputLayoutPassword.setError(null);
                    return true;
                }
            }
        });

    }
}
