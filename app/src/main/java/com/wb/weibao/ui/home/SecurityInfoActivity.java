package com.wb.weibao.ui.home;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

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
import com.wb.weibao.databinding.ActivitySecurityInfoBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.DeviceTypeModel;
import com.wb.weibao.model.home.ProjectDetailbean;
import com.wb.weibao.model.home.SecurityInfoModel;
import com.wb.weibao.ui.record.RecordDetailActivity;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

public class SecurityInfoActivity extends BaseActivity<BasePresenter, ActivitySecurityInfoBinding> {
    private ArrayList<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private int mType = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_security_info;
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

    @Override
    protected void initData() {
        super.initData();
        String id = getIntent().getStringExtra("id");
        mType = getIntent().getIntExtra("type", -1);
        getDataList2(getIntent().getStringExtra("projectid"));
        getDataList(id);
        initAdapter();



    }
 public SecurityInfoModel baseBeans;
    /**
     * 我的维保
     */
    public void getDataList(String id) {
        Api.getApi().getMyWeiBaoInfo(id, MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<SecurityInfoModel>(this, true) {
                    @Override
                    public void onSuccess(SecurityInfoModel baseBean) {
                        baseBeans=baseBean;
                        initView(baseBean);
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    public void getDataList2(String id) {

        Api.getApi().getMyWeiBaoInfodetail(id, MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectDetailbean>(this, true) {
                    @Override
                    public void onSuccess(ProjectDetailbean baseBean) {
                        mBinding.tv5.setText("项目名称：" + baseBean.getData().getName()+ "\n项目地址：" + baseBean.getData().getArea());


                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }
   public String type="";
    private void initView(SecurityInfoModel baseBean) {
        if (baseBean.getData() == null) {
            return;
        }
        SecurityInfoModel.DataBean data = baseBean.getData();
        String state = "";
        String time = "";
        String CreateTime = data.getCreateTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(data.getCreateTime(), "yyyy.MM.dd HH.mm.ss");
        String ProcessingTime = data.getProcessingTime() == 0 ? "" : DemoUtils.ConvertTimeFormat(data.getProcessingTime(), "yyyy.MM.dd HH.mm.ss");
        switch (data.getStatus()) {
            case "1"://待审核
                state = "待审批";
                mBinding.tv4.setVisibility(View.GONE);
                mBinding.tvInfo1.setVisibility(View.GONE);

                break;
            case "2"://审核失败
                state = "审批失败";
                mBinding.tv4.setVisibility(View.GONE);
                mBinding.tvInfo1.setVisibility(View.GONE);
                break;
            case "3"://待维保
                state = "待维保";
                mBinding.tv4.setVisibility(View.VISIBLE);
                mBinding.tvInfo1.setVisibility(View.GONE);
                time = "审核时间：" + CreateTime;

                if (mType == 1) {
                    mBinding.llyButtom.setVisibility(View.VISIBLE);
                    mBinding.tvFail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            startActivityForResult(new Intent(aty, HandleMaintenanceActivity.class).putExtra("id", data.getId() + "")
                                    .putExtra("name", data.getPrincipalName()).putExtra("type", "5"), 1001);
                        }
                    });
                    mBinding.tvOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivityForResult(new Intent(aty, HandleMaintenanceActivity.class).putExtra("id", data.getId() + "")
                                    .putExtra("name", data.getPrincipalName()).putExtra("type", "4"), 1001);
                        }
                    });
                }


                break;
            case "4"://维保成功
                state = "已完成";
                mBinding.tv4.setVisibility(View.VISIBLE);
                mBinding.tvInfo1.setVisibility(View.GONE);
                time = "审核时间：" + CreateTime + "\n完成时间：" + ProcessingTime;
                break;
            case "5"://维保失败
                state = "维保失败";
                mBinding.tv4.setVisibility(View.VISIBLE);
                mBinding.tvInfo1.setVisibility(View.GONE);
                time = "审核时间：" + CreateTime;
                break;
            default://已取消
                state = "已取消";
                mBinding.tv4.setVisibility(View.GONE);
                mBinding.tvInfo1.setVisibility(View.GONE);
                break;
        }

        getMaintenanceOrderTypeList(data.getType());
        mBinding.tv1.setText("订单号：" + data.getOrderNo() + "\n订单状态：" + state
                + "\n提交时间：" + DemoUtils.ConvertTimeFormat(data.getCreateTime(), "yyyy.MM.dd HH.mm.ss"));
        mBinding.tv2.setText("故障类型：" + data.getFaultTypeName() + "\n设备类型：" + data.getEquipmentTypeName()
                + "\n维保类型：" + type);
        mBinding.tvInfo.setText("详情描述：" + data.getMemo());
        mBinding.tv3.setText("维保发起人：" + data.getPrincipalName() + "\n发起人电话：" + data.getPrincipalPhone());

        mBinding.tv4.setText(time
                + "\n维保联系人：孙先生\n维保联系人电话：0571- 56260119/ 13123941267"

        );
        mBinding.tvInfo1.setText("详情描述：" + data.getMemo());
        // http://47.110.184.79:8300/earlywarn/oss/download?fileName=1BE0532D-4CA0-4FD7-8504-F384A0F37CAE.png
        if (!TextUtils.isEmpty(data.getPicturesOssKeys())) {
            String[] split = data.getPicturesOssKeys().split(";");
            if (split != null && split.length > 0) {
                for (String str : split) {
                    mImgs.add(Link.SEREVE+"oss/download?fileName=" + str);
                }
            }
            mAdapter.notifyDataSetChanged();
        }

        mBinding.gvBody.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent();
                in.setClass(SecurityInfoActivity.this, MaxPictureActivity.class);
                //Will pass, I click for the current position
                in.putExtra("pos", position);
                //Will pass,Photos to show the pictures of the collection address
                in.putStringArrayListExtra("imageAddress", mImgs);
                startActivity(in);
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


    /**
     * 维保
     */
    private void getMaintenanceOrderTypeList(String types) {
        Api.getApi().getTypeListname( ""+MyApplication.getInstance().getUserData().getPrincipal().getUserId(),types, "maintenanceOrderType")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        if (baseBeans.getData() == null) {
                            return;
                        }
                        SecurityInfoModel.DataBean data = baseBeans.getData();
                        mBinding.tv2.setText("故障类型：" + data.getFaultTypeName() + "\n设备类型：" + data.getEquipmentTypeName()
                                + "\n维保类型：" + baseBean.getData().toString());
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


}
