package com.wb.weibao.ui.home;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.Link;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityQrcodeBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.ChangEvent;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.AppManager;
import com.wb.weibao.utils.QrCodeUtil;
import com.wb.weibao.utils.picker.util.LogUtils;

import org.greenrobot.eventbus.EventBus;

public class QrcodeActivity extends BaseActivity<BasePresenter,ActivityQrcodeBinding> {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode;
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

        mTitleBarLayout.setTitle("我要接班");
    }

    @Override
    protected void initData() {
        super.initData();

//
        Api.getApi().getQrcode(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        com.lidroid.xutils.util.LogUtils.d("BaseBean=="+baseBean.toString());
                        String url= Link.SEREVE+"handover/proccess?msg={"+baseBean.getData().toString()+"}&userId={"+MyApplication.getInstance().getUserData().getId()+"}";
                        mBinding.imageView.setImageBitmap(QrCodeUtil.createQRCode(url,600));
                        getQrcodeResult();
                    }

                    @Override
                    public void onFail(String errMsg) {
                           finish();
                    }
                });



    }
    private int num;
    private Handler handler;

    public void getQrcodeResult()
    {
        Api.getApi().getQrcodeResult(MyApplication.getInstance().getUserData().getId() + "")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, false) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        com.lidroid.xutils.util.LogUtils.d("BaseBean=="+baseBean.toString());
                         showToast("接班成功");
                        EventBus.getDefault().post(new ChangEvent());
                        finish();
                    }

                    @Override
                    public void onFail(String errMsg) {
                        if(num>=5)
                        {
                            showToast("交接班超时,请重试");
                            EventBus.getDefault().post(new ChangEvent());
                            finish();
                            return;
                        }
                        num++;
                        handler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                getQrcodeResult();
                            }
                        };
                        handler.sendEmptyMessageDelayed(0, 10000);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null) {
            handler.removeMessages(0);
        }
    }
}
