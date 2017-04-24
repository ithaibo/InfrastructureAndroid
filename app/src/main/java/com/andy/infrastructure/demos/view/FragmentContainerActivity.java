package com.andy.infrastructure.demos.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.ListDialogItemDataBean;
import com.andy.infrastructure.databinding.ActFragmentContainerBinding;
import com.andy.infrastructure.demos.view.custome_view.CircleViewFragment;
import com.andy.infrastructure.demos.view.custome_view.CustomeHorizontalScrollViewFragment;
import com.andy.infrastructure.dialog.ListMenuDialog;
import com.andy.infrastructure.presenter.ListDialogItemPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/3/17.
 */

public class FragmentContainerActivity extends BaseActivity implements View.OnClickListener, ListDialogItemPresenter<Fragment> {
    private ActFragmentContainerBinding binding;
    private ListMenuDialog<Fragment> fragmentMenuListDialog;
    private List<ListDialogItemDataBean<Fragment>> menuDialogDataList;

    @Override
    protected int getLayoutId() {
        return R.layout.act_fragment_container;
    }

    @Override
    protected void initData() {
        if (menuDialogDataList == null) {
            menuDialogDataList = new ArrayList<>();
            menuDialogDataList.add(
                    new ListDialogItemDataBean<>(
                            new CircleViewFragment(), "CircleView", this)
            );
            menuDialogDataList.add(
                    new ListDialogItemDataBean<>(
                            new CustomeHorizontalScrollViewFragment(),
                            "CustomeHorizontalScrollView",
                            this
                    )
            );
        }
    }

    @Override
    protected void initViews() {
        binding = (ActFragmentContainerBinding) mDataBind;
        binding.contentViewRoot.fabShowMenu.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        doReplaceFragment(menuDialogDataList.get(0).getItemData());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_show_menu) {
            showMenuListDialog();
        }
    }

    @Override
    public void present(Fragment fragClass) {
        doReplaceFragment(fragClass);
    }

    /**
     * 显示菜单Dialog
     */
    private void showMenuListDialog() {
        if (fragmentMenuListDialog == null) {
            fragmentMenuListDialog = new ListMenuDialog<>();
            fragmentMenuListDialog.setDataSet(menuDialogDataList);
        }
        fragmentMenuListDialog.show(getSupportFragmentManager(), "FramentMenu");
    }

    public void doReplaceFragment(Fragment fragment) {
        if (!fragment.isAdded()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(binding.contentViewRoot.flFrgContent.getId(), fragment);
            ft.commit();
        }
        if (fragmentMenuListDialog != null) {
            fragmentMenuListDialog.dismissAllowingStateLoss();
        }
    }

}
