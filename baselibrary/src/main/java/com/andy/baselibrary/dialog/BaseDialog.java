package com.andy.baselibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Andy on 2016/12/23.
 */

public abstract class BaseDialog extends DialogFragment {
    protected Context mContext;
    private Dialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), getStyle());

        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getLayoutId(), null, false);

        ButterKnife.bind(this, dialog);

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
