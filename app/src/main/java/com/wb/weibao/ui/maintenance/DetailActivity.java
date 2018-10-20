package com.wb.weibao.ui.maintenance;

import android.content.Intent;
import android.view.View;

import com.lm.lib_common.base.BaseActivity;
import com.lm.lib_common.base.BaseNetListener;
import com.lm.lib_common.base.BasePresenter;
import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.databinding.ActivityDetailBinding;
import com.wb.weibao.model.event.DetailBean;
import com.wb.weibao.utils.DemoUtils;

public class DetailActivity extends BaseActivity<BasePresenter,ActivityDetailBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
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
        mTitleBarLayout.setTitle("新增维保订单");
    }
    private String mId = "";
    private String muserId = "";
    private String mstatus = "";
    private String mhasProcessing;
    @Override
    protected void initData() {
        super.initData();
       mId=getIntent().getStringExtra("id");
       muserId=getIntent().getStringExtra("userId");
        mstatus=getIntent().getStringExtra("status");
        mhasProcessing=getIntent().getStringExtra("hasProcessing");
        Api.getApi().getorderDetail(muserId, mId)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<DetailBean>(this, true) {
                    @Override
                    public void onSuccess(DetailBean baseBean) {
                        switch (baseBean.getData().getStatus()) {
                            case "1":
                                mBinding.tv2.setText("待平台定价");
                                break;
                            case "2":
                                mBinding.tv2.setText("用户撤销");

                                break;
                            case "3":
                                mBinding.tv2.setText("代交预付款");

                                break;
                            case "4":
                                mBinding.tv2.setText("付款失败");

                                break;
                            case "5":
                                mBinding.tv2.setText("待维保");

                                break;
                            case "6":
                                mBinding.tv2.setText("维保中");

                                break;
                            case "7":
                                mBinding.tv2.setText("失效");

                                break;
                            default:
                                mBinding.tv2.setText("完成");
                                break;
                        }
                        mBinding.tv1.setText(baseBean.getData().getOrderNo());
                        mBinding.tv3.setText(baseBean.getData().getPrincipalName());
                        mBinding.tv4.setText(baseBean.getData().getPrincipalPhone());
                        mBinding.tv5.setText(DemoUtils.ConvertTimeFormat(baseBean.getData().getCreateTime(), "yyyy.MM.dd"));
                        mBinding.tv6.setText(baseBean.getData().getMemo());
                        mBinding.tv7.setText("￥"+baseBean.getData().getAmount());
                        mBinding.tv8.setText(DemoUtils.ConvertTimeFormat(baseBean.getData().getCreateTime(), "yyyy.MM.dd"));
                        if(baseBean.getData().getProcessingName()!=null) {
                            mBinding.tv9.setText("" + baseBean.getData().getProcessingName());
                        }
                        mBinding.tv10.setText("￥"+baseBean.getData().getEndAmount());
                        if(baseBean.getData().getProcessingTime()!=null) {
                            mBinding.tv11.setText(DemoUtils.ConvertTimeFormat((Long) baseBean.getData().getProcessingTime(), "yyyy.MM.dd"));
                        }
                        if(baseBean.getData().getProcessingRet()!=null) {
                            mBinding.tv12.setText("" + baseBean.getData().getProcessingRet());
                        }
                        switch (mstatus)
                        {
                            case "1":
                            case "2":
                            case "7":
                                mBinding.rly1.setVisibility(View.VISIBLE);
                                mBinding.rly2.setVisibility(View.VISIBLE);

                                break;
                            case "3":
                            case "4":
                            case "5":
                            case "6":
                                mBinding.rly1.setVisibility(View.VISIBLE);
                                mBinding.rly2.setVisibility(View.VISIBLE);
                                mBinding.rly3.setVisibility(View.VISIBLE);
                                if(mstatus.equals("5") && mhasProcessing.equals("true"))
                                {
                                    mBinding.tvTurn.setVisibility(View.VISIBLE);
                                }
                                if(mstatus.equals("6") && mhasProcessing.equals("true"))
                                {
                                    mBinding.tvTurn.setText("订单完成");
                                    mBinding.tvTurn.setVisibility(View.VISIBLE);
                                }
                                break;
                            case "8":
                                mBinding.rly1.setVisibility(View.VISIBLE);
                                mBinding.rly2.setVisibility(View.VISIBLE);
                                mBinding.rly3.setVisibility(View.VISIBLE);
                                mBinding.rly4.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

        mBinding.tvTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBinding.tvTurn.getText().equals("订单完成")) {
                    startActivity(FeedbackActivity.class);
                }else
                {
                    Api.getApi().getorderUpdate(muserId,"6",mId).compose(callbackOnIOToMainThread())
                            .subscribe(new BaseNetListener<BaseBean>(DetailActivity.this, false) {
                                @Override
                                public void onSuccess(BaseBean baseBean) {
                                    Intent intent=new Intent(DetailActivity.this,FeedbackActivity.class);
                                    intent.putExtra("userId", muserId);
                                    intent.putExtra("id", mId);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFail(String errMsg) {

                                }
                            });
                }
            }
        });

    }
}
