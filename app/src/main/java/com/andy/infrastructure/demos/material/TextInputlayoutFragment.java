package com.andy.infrastructure.demos.material;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.andy.baselibrary.fragment.DataBindFrgment;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.UserBean;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Andy on 2017/3/8.
 */

public class TextInputlayoutFragment extends DataBindFrgment {

    @Override
    protected int getFrgLayoutId() {
        return R.layout.frg_material_text_input_layout;
    }

    @Override
    protected void initView(View rootView) {
        FrgTextInputDaBind textInputDataBind = (FrgTextInputDaBind) dataBinder;

        UserBean userBean = new UserBean();
        textInputDataBind.setUserData(userBean);

        initInputLayoutParams(textInputDataBind);
    }

    private void initInputLayoutParams(final FrgTextInputDaBind dataBind) {
        dataBind.inputLayoutUsername.setHint(getResources().getString(R.string.label_username));
        dataBind.inputLayoutPassword.setHint(getResources().getString(R.string.label_password));

        Observable.create(new Observable.OnSubscribe<UserBean>() {
            @Override
            public void call(final Subscriber<? super UserBean> subscriber) {
                dataBind.btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        subscriber.onNext(dataBind.getUserData());
                    }
                });
            }
        }).filter(new Func1<UserBean, Boolean>() { //用户名是否合法
            @Override
            public Boolean call(UserBean userBean) {
                if(userBean==null ||
                        TextUtils.isEmpty(userBean.userName.get()))
                {
                    dataBind.inputLayoutUsername.setError("Username cannot be null!");
                    return false;
                } else {
                    dataBind.inputLayoutUsername.setError(null);
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
                    dataBind.inputLayoutPassword.setError("Password Wrong!");
                    return false;
                } else {
                    dataBind.inputLayoutPassword.setError(null);
                    return true;
                }
            }
        });

//        dataBind.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(dataBind.getUserData().userName.get())) {
//                    dataBind.inputLayoutUsername.setError("Username cannot be null!");
//                }
//
//                if (TextUtils.isEmpty(dataBind.getUserData().password.get()) ||
//                        !"123456".equals(dataBind.getUserData().password.get())) {
//                    dataBind.inputLayoutPassword.setError("Password Wrong!");
//                    dataBind.inputLayoutUsername.setError(null);
//                }
//            }
//        });
    }
}
