package com.wb.weibao.ui.home;

import android.net.http.SslError;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivityTrainingEducationBinding;
import com.wb.weibao.utils.WebViewWithProgress;

import java.util.ArrayList;
import java.util.List;

public class TrainingEducationActivity extends BaseActivity<BasePresenter, ActivityTrainingEducationBinding> {
    private List<String> mDataList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_training_education;
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
        mTitleBarLayout.setTitle("培训教育");
    }
    WebView mWebView;
    WebViewWithProgress mwebViewWithProgress;
    @Override
    protected void initData() {
        super.initData();
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mDataList.add("");
//        mAdapter = new CommonAdapter<String>(aty, R.layout.item_train_education_layout, mDataList) {
//            @Override
//            protected void convert(ViewHolder holder, String s, int position) {
//
//            }
//        };
//        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));
//        mBinding.rcBody.setAdapter(mAdapter);
        initViews2("http://m.119.cn/");
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initViews2(String url) {
        //

        mwebViewWithProgress = (WebViewWithProgress) findViewById(R.id.detail_webview);
        mWebView = mwebViewWithProgress.getWebView();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("GBK");
        mWebView.getSettings().setBlockNetworkImage(false);

        if (Build.VERSION.SDK_INT >= 21) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
//		//设置webview为单列显示，是一些大图片适应屏幕宽度
//		mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); // Android默认的处理方式
                handler.proceed();  // 接受所有网站的证书
                //handleMessage(Message msg); // 进行其他处理

            }
        });


        mWebView.loadUrl(url);
    }
}
