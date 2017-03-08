package com.andy.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by Andy on 2017/3/8.
 *
 * 在使用该类的时候，可以用dataBinding去执行相关绑定。
 */

public abstract class DataBindBaseDialog extends DialogFragment {
    protected Context mContext;
    private Dialog dialog;
    protected ViewDataBinding dataBinding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), getStyle());

        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getLayoutId(), null, false);

        dataBinding = DataBindingUtil.bind(view);

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    protected abstract int getLayoutId();
    protected abstract int getStyle();

    public void setContentView(View view){
        dialog.setContentView(view);
    }
}
