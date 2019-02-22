package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityChangeShiftsBinding;
import com.wb.weibao.databinding.ItemChangeShiftsLayoutBinding;
import com.wb.weibao.widget.zxing.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class ChangeShiftsActivity extends BaseActivity<BasePresenter, ActivityChangeShiftsBinding> {
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final int REQUEST_CODE_SCAN = 2001;//二维码扫码参数
    private List<String> mDataList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_shifts;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("交接班");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvJiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aty,
                        CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mDataList.add("");
        mAdapter = new CommonAdapter<String>(aty, R.layout.item_change_shifts_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ItemChangeShiftsLayoutBinding binding = holder.getBinding(ItemChangeShiftsLayoutBinding.class);
                binding.viewLine.setVisibility(position == mDataList.size() - 1 ? View.GONE : View.VISIBLE);
            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.rcBody.setAdapter(mAdapter);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);


            }
        }
    }
}
