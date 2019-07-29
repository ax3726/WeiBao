package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.wb.weibao.R;
import com.wb.weibao.adapters.abslistview.CommonAdapter;
import com.wb.weibao.adapters.abslistview.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.Link;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityWeibaoDetailBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.MaintenanceRecordDetailBean;
import com.wb.weibao.model.home.ProjectDetailbean;
import com.wb.weibao.model.home.SecurityInfoModel;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.blankj.utilcode.util.PermissionUtils.PermissionActivity.start;

public class WeibaoDetailActivity extends BaseActivity<BasePresenter,ActivityWeibaoDetailBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_weibao_detail;
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

        mTitleBarLayout.setTitle("维保详情");
    }

    @Override
    protected void initEvent() {
        super.initEvent();

    }


    private ArrayList<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private int mType = -1;

    @Override
    protected void initData() {
        super.initData();
        String id = getIntent().getStringExtra("id");
        getDataList(id);
        initAdapter();


mBinding.gvBody.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent();
        in.setClass(WeibaoDetailActivity.this, MaxPictureActivity.class);
        //Will pass, I click for the current position
        in.putExtra("pos", position);
        //Will pass,Photos to show the pictures of the collection address
        in.putStringArrayListExtra("imageAddress", mImgs);
        startActivity(in);
    }
});

    }

    /**
     * 我的维保
     */
    public void getDataList(String id) {
        Api.getApi().getMaintenanceRecordDetail(id)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<MaintenanceRecordDetailBean>(this, true) {
                    @Override
                    public void onSuccess(MaintenanceRecordDetailBean baseBean) {
                        if (baseBean.getData() == null) {
                            return;
                        }

                        String createTime=baseBean.getData().getCreateTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(baseBean.getData().getCreateTime(), "yyyy-MM-dd HH:mm:ss");

                        String maintenanceDate=baseBean.getData().getMaintenanceDate() == 0 ? "" : DemoUtils.ConvertTimeFormat(baseBean.getData().getMaintenanceDate(), "yyyy-MM-dd");

                        String maintenanceNextDate=baseBean.getData().getMaintenanceNextDate() == 0 ? "" : DemoUtils.ConvertTimeFormat(baseBean.getData().getMaintenanceNextDate(), "yyyy-MM-dd");

                        mBinding.tv1.setText("上报时间：   "+createTime);
                        mBinding.tvWeixiu.setText("维修单位：   "+baseBean.getData().getProjectName());
                        mBinding.tv2.setText("被维修单位：   "+baseBean.getData().getCoverProjectName());
                        mBinding.tv3.setText("维保联系人：   "+baseBean.getData().getContractName());
                        mBinding.tv4.setText("联系人电话：   "+baseBean.getData().getContractPhone());
                        mBinding.tv5.setText("维保内容：   "+baseBean.getData().getMaintenanceContent());
                        mBinding.tv6.setText("维保时间：   "+maintenanceDate);
                        mBinding.tv7.setText("下次维保时间：   "+maintenanceNextDate);
                        if (!TextUtils.isEmpty(baseBean.getData().getPicturesOssKeys())) {
                            String[] split = baseBean.getData().getPicturesOssKeys().split(";");
                            if (split != null && split.length > 0) {
                                for (String str : split) {
                                    mImgs.add(Link.SEREVE+"oss/download?fileName=" + str);
                                }
                            }
                            mAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }




    private void initAdapter() {
        mAdapter = new CommonAdapter<String>(aty, R.layout.item_add_point_img_layout, mImgs) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                ImageView img = viewHolder.getView(R.id.img);
                ImageView img_del = viewHolder.getView(R.id.img_del);
                img_del.setVisibility(View.GONE);
                GlideUrl glideUrl = new GlideUrl(item, new LazyHeaders.Builder()
                        .addHeader("Cookie", "JSESSIONID=" + MyApplication.getInstance().getJSESSIONID())
                        .build());
                Glide.with(aty).load(glideUrl).into(img);

            }
        };
        mBinding.gvBody.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            finish();
        }
    }




}
