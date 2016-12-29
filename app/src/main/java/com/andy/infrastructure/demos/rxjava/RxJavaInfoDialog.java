package com.andy.infrastructure.demos.rxjava;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.andy.baselibrary.dialog.BaseDialog;
import com.andy.infrastructure.R;
import com.andy.infrastructure.interfaces.ConfirmListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Andy on 2016/12/28.
 */

public class RxJavaInfoDialog extends BaseDialog {

    @BindView(R.id.flContentView)
    FrameLayout contentView;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    private ConfirmListener confirmListener;

    @Override
    protected int getLayoutId() {
        return R.layout.dlg_rxjava_info;
    }

    @Override
    protected int getStyle() {
        return R.style.BaseDialog;
    }

    @OnClick({R.id.btnConfirm})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm:
                if (this.confirmListener != null ) {
//                    this.confirmListener.onConfirm(this.tvResponse.getText().toString());
                }
                dismiss();
                break;
        }
    }

    public void setConfirmListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    public void setChildViews(View childView) {
//        getDialog().setContentView(childView);
        if (contentView.getChildCount()!=0) {
            contentView.removeAllViews();
        }
        contentView.addView(childView);
        contentView.measure(View.MeasureSpec.AT_MOST, View.MeasureSpec.AT_MOST);
    }
}
