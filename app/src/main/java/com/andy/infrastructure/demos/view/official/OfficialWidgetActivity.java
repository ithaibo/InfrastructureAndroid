package com.andy.infrastructure.demos.view.official;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.andy.baselibrary.activity.DataBindActivity;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.view.OfficialWidgetBinding;

/**
 * Created by Smily on 2017/5/20.
 */

public class OfficialWidgetActivity extends DataBindActivity<OfficialWidgetBinding> implements TextWatcher {
    @Override
    protected int getLayoutId() {
        return R.layout.view_act_official_widget;
    }

    @Override
    protected void initData() {
        mDataBind.setTextWatcher(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        LogUtil.i("text changed");
        String sC = s.toString();
        if (TextUtils.isEmpty(sC)){
            mDataBind.tilPassword.setError("密码不能为空");
        } else {
            if (sC.contains("#")){
                mDataBind.tilPassword.setError("不能包含特殊字符");
            }
        }
    }

    /**
     * 该方法主要是为了给EditText设置一个TextWatcher，
     * EditText提供的方法是addTextChangeListener，
     * 因此需要使用BindingAdapter来转换。
     * @param et
     * @param wt
     */
    @BindingAdapter("textWatcher")
    public static void setTextWatcher(EditText et, TextWatcher wt) {
        et.addTextChangedListener(wt);
    }
}
