package com.andy.infrastructure.demos.lifecycle;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.FrgProductListBinding;
import com.andy.infrastructure.demos.lifecycle.entity.ProductEntity;

import java.util.List;

/**
 * Created by Andy on 2017/9/29.
 */

public class ProductListFragment extends LifecycleFragment {

    private FrgProductListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_product_list, container, false);
        mBinding.setAdapter(new ProductAdapter(this.clickCallback));
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProductListViewModule listViewModule = ViewModelProviders.of(this).get(ProductListViewModule.class);
        listViewModule.getProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> productEntities) {
                if (productEntities!=null) {
                    mBinding.setIsLoading(false);
                    mBinding.getAdapter().setProductList(productEntities);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    private ProductClickCallback clickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            Toast.makeText(getContext(), product.getName() + product.getDescription() + product.getPrice(), Toast.LENGTH_SHORT).show();
        }
    };
}
