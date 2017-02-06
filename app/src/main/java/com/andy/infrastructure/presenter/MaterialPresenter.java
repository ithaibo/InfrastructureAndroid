package com.andy.infrastructure.presenter;

import android.view.View;

import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DataFrg;

/**
 * Created by Andy on 2017/2/6.
 */

public class MaterialPresenter {
    public void onClick(View view, DataFrg dataFrg) {
        switch (view.getId()) {
            case R.id.btn_change_text:
                dataFrg.setCbText("Text is ...");
                break;
        }
    }
}
