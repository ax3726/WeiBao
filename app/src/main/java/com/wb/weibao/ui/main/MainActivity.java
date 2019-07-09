package com.wb.weibao.ui.main;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RadioGroup;

import com.lidroid.xutils.util.LogUtils;
import com.lm.lib_common.utils.DoubleClickExitHelper;
import com.wb.weibao.BuildConfig;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.common.PlayNumService;
import com.wb.weibao.common.TimeService;
import com.wb.weibao.databinding.ActivityMainBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.VersionBean;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.ErrorEvent;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.Login.LoginActivity;
import com.wb.weibao.ui.earlywarning.FireFragment;
import com.wb.weibao.ui.earlywarning.WarningFragment;
import com.wb.weibao.ui.home.HomeFragment;
import com.wb.weibao.ui.maintenance.AddOrderActivity;
import com.wb.weibao.ui.mine.MineFragment;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;
import com.wb.weibao.utils.update.AppUpdateProgressDialog;
import com.wb.weibao.utils.update.DownloadReceiver;
import com.wb.weibao.utils.update.DownloadService;
import com.wb.weibao.view.PopupWindow.FitPopupUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BasePresenter, ActivityMainBinding> implements View.OnClickListener {

    private FragmentManager mFm;
    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments = new ArrayList<>();
    private DoubleClickExitHelper mDoubleClickExit;//
    private HomeFragment mHomeFragment;
    private WarningFragment WarningFragment;
    /*  private RecordFragment mRecordFragment;
      private MainTenanceFragment mMainTenanceFragment;*/
    private MineFragment mMineFragment;
    private List<ProjectListModel.DataBean.ListBean> mProjectList = new ArrayList<>();//项目列表
    private int mIndex = 0;//当前模块下标
    private SpfUtils spfUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvName.setOnClickListener(this);
        mBinding.tvAddOrder.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        super.initData();

        EventBus.getDefault().register(this);

        spfUtils = SpfUtils.getInstance(MainActivity.this);
        updateapp();

        mBinding.rgButtom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        if (currentFragmentPosition != 0) {
                            mIndex = 0;
                            mBinding.rlyHead.setVisibility(View.GONE);
                            mBinding.tvAddOrder.setVisibility(View.GONE);
                            changeFragment(0);
                        }
                        break;
                    case R.id.rb_forewarning:
                        if (currentFragmentPosition != 1) {
                            mIndex = 1;
                            mBinding.rlyHead.setVisibility(View.GONE);
                            mBinding.tvAddOrder.setVisibility(View.GONE);
                            toLoadData();
                            changeFragment(1);
                        }
                        break;
                   /* case R.id.rb_record:
                        if (currentFragmentPosition != 1) {
                            mIndex = 1;
                            mBinding.rlyHead.setVisibility(View.VISIBLE);
                            mBinding.tvAddOrder.setVisibility(View.GONE);
                            changeFragment(1);

                        }
                        break;
                    case R.id.rb_weibao:
                        if (currentFragmentPosition != 2) {
                            mIndex = 2;
                            mBinding.rlyHead.setVisibility(View.VISIBLE);
                            mBinding.tvAddOrder.setVisibility(View.VISIBLE);
                            changeFragment(2);
                        }
                        break;*/
                    case R.id.rb_myuser:
                        if (currentFragmentPosition != 2) {
                            mBinding.rlyHead.setVisibility(View.GONE);
                            mIndex = 2;
                            changeFragment(2);
                        }
                        break;
                }
            }
        });
        //  getProjectList();


    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDoubleClickExit = new DoubleClickExitHelper(this);
        initFragment();
        setJPush();
        //启动后台服务
        Intent service = new Intent(this, TimeService.class);
        startService(service);

    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        WarningFragment = new WarningFragment();
       /* mRecordFragment = new RecordFragment();
        mMainTenanceFragment = new MainTenanceFragment();*/
        mMineFragment = new MineFragment();

        mFragments.add(mHomeFragment);
        mFragments.add(WarningFragment);
        /*mFragments.add(mRecordFragment);
        mFragments.add(mMainTenanceFragment);*/
        mFragments.add(mMineFragment);
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();

        mTransaction.add(R.id.lly_body, mHomeFragment);
        mTransaction.show(mFragments.get(0));
        mTransaction.commitAllowingStateLoss();
    }

    public void toChange(int index)
    {
        if (currentFragmentPosition != 1) {
            mBinding.rgButtom.check(R.id.rb_forewarning);
            mIndex = 1;
            mBinding.rlyHead.setVisibility(View.GONE);
            mBinding.tvAddOrder.setVisibility(View.GONE);
            toLoadData();
            changeFragment(1);
        }
    }

    private int currentFragmentPosition = 0;

    public void changeFragment(final int position) {
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();
        if (position != currentFragmentPosition) {
            mTransaction.hide(mFragments.get(currentFragmentPosition));
            if (!mFragments.get(position).isAdded()) {
                mTransaction.add(R.id.lly_body, mFragments.get(position));
            }
            mTransaction.show(mFragments.get(position));
            mTransaction.commitAllowingStateLoss();
        }
        currentFragmentPosition = position;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClickExit.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获取项目列表
     */
    private void getProjectList() {
        Api.getApi().getProject_list(MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",
                "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId()).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            mProjectList.clear();
                            if (data.getList() != null && data.getList().size() > 0) {
                                mProjectList.addAll(data.getList());
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);

                                if (!TextUtils.isEmpty(spfUtils.getSpfString(SpfKey.INST_ID))) {

                                    MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                                    mBinding.tvName.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
                                } else {
                                    spfUtils.setSpfString(SpfKey.INST_ID, "" + listBean.getId());
                                    spfUtils.setSpfString(SpfKey.INST_NAME, listBean.getName());
                                    MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                                    mBinding.tvName.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
                                }


                                toLoadData();

                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    private void toLoadData() {

        if (WarningFragment != null) {
            WarningFragment.toLoadData();
        }


     /*   if (mRecordFragment != null) {
            mRecordFragment.loadData();
        }*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_order:
                startActivity(AddOrderActivity.class);
                break;
            case R.id.tv_name:
                List<String> wheelString = new ArrayList<>();
                for (int i = 0; i < mProjectList.size(); i++) {
                    wheelString.add(mProjectList.get(i).getName());
                }
                if (wheelString.size() > 0) {
                    FitPopupUtil fitPopupUtil = new FitPopupUtil(aty, wheelString);
                    fitPopupUtil.setOnClickListener(new FitPopupUtil.OnCommitClickListener() {
                        @Override
                        public void onClick(String reason) {
                            ProjectListModel.DataBean.ListBean listBean = mProjectList.get(Integer.parseInt(reason));
                            spfUtils.setSpfString(SpfKey.INST_ID, "" + listBean.getId());
                            spfUtils.setSpfString(SpfKey.INST_NAME, listBean.getName());
                            MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                            mBinding.tvName.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
//                            MyApplication.getInstance().setProjectId(listBean.getInstId());
//                            mBinding.tvName.setText(listBean.getName());
                            toLoadData();

                        }
                    });
                    fitPopupUtil.showPopup(v);
                }
                break;
        }
    }

    /**
     * 设置推送
     */
    private void setJPush() {
        if (MyApplication.getInstance().getRegistrationID() != null) {
            Api.getApi().setJPush(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", MyApplication.getInstance().getRegistrationID())
                    .compose(callbackOnIOToMainThread())
                    .subscribe(new BaseNetListener<BaseBean>(this, true) {
                        @Override
                        public void onSuccess(BaseBean baseBean) {

                        }

                        @Override
                        public void onFail(String errMsg) {

                        }
                    });
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(ErrorEvent event) {
        getErrorList();

    }

    private int num = 0;
    MediaPlayer mMediaPlayer = null;

    /**
     * 获取预警列表
     */
    public void getErrorList() {

        Api.getApi().getRecordcount(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordCount>(MainActivity.this, false) {
                    @Override
                    public void onSuccess(RecordCount baseBean) {
                        LogUtils.e("baseBean" + baseBean.toString());
                        if(baseBean.getData().getFireWaitConfirmNum()>0)
                        {
                            MyApplication.getInstance().setErrorlist("1");
                                geterrortoast();
                        }

                    }

                    @Override
                    public void onFail(String errMsg) {
                        MyApplication.getInstance().setErrorlist("0");
                    }
                });



    }

    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer2;

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

    }

    //    Camera camera = Camera.open();
//    Camera.Parameters p = camera.getParameters();
    Camera.Parameters p = null;
    Camera camera = null;
    private boolean mIsLight = false;

    //手电筒闪光开启
    private void processOnFlash() {

        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        if (camera == null) {
            camera = Camera.open();
            p = camera.getParameters();
        }

        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
//        if (!mIsLight) {
//            if(camera!=null) {
//                camera.stopPreview();
//                camera.release();
//                camera = null;
//            }
//            if (camera == null) {
//                camera = Camera.open();
//            }
//            camera.startPreview();
//            Camera.Parameters parameters = camera.getParameters();
//            List<String> flashModes = parameters.getSupportedFlashModes();
//            if (flashModes == null) {
//                return;
//            }
//            String flashMode = parameters.getFlashMode();
//            if (!flashMode.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
//                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//                camera.setParameters(parameters);
//            }
//            mIsLight = true;
//        }


    }

    //手电筒闪光关闭
    private void processOffFlash() {
        if (camera != null) {
            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            camera.stopPreview();
            if (camera != null) {
                camera.stopPreview();
                camera.release();
                camera = null;
            }
        }
//        if (mIsLight) {
//            Camera.Parameters parameters = camera.getParameters();
//            List<String> flashModes = parameters.getSupportedFlashModes();
//            if (flashModes == null) {
//                return;
//            }
//            String flashMode = parameters.getFlashMode();
//            if (!flashMode.contains(Camera.Parameters.FLASH_MODE_OFF)) {
//                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//                camera.setParameters(parameters);
//            }
//            mIsLight = false;
//            if(camera!=null) {
//                camera.stopPreview();
//                camera.release();
//                camera = null;
//            }
//        }

    }


    public void geterrortoast() {

        //唤醒屏幕
//        DemoUtils.wakeUpAndUnlock(MyApplication.getInstance());
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //直接创建，不需要设置setDataSource
        if (mMediaPlayer == null && "ok".equals(SpfUtils.getInstance(MyApplication.getInstance()).getSpfString(SpfKey.IS_PUSH_PLAY)) && !PlayNumService.getIntance().isIsPlay()) {

            //震动

            long[] patter = {1000, 1000, 2000, 50};
            vibrator.vibrate(patter, 0);

            if (isCameraUseable() == true) {
                countDownTimer = new CountDownTimer(2 * 1000 + 50, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        if ((millisUntilFinished / 1000) % 2 == 0) {
                            processOffFlash();
                        } else {
                            processOnFlash();
                        }
                    }

                    @Override
                    public void onFinish() {
                        processOffFlash();

                    }
                };
                countDownTimer.start();
            }
            PlayNumService.getIntance().setIsPlay(true);
            mMediaPlayer = MediaPlayer.create(MyApplication.getInstance(), R.raw.huojing);
            //                mMediaPlayer.setLooping(false);//设置是否循环播放
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    num++;
                    if (num == 3) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.stop();
                            mMediaPlayer.release();
                            vibrator.cancel();
                        }
                        num = 0;
                        mMediaPlayer = null;
                        PlayNumService.getIntance().setIsPlay(false);
                    } else {
                        mMediaPlayer.start();
                    }

                }
            });
        } else {
            vibrator.cancel();
        }
    }


    public static boolean isCameraUseable() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
// setParameters 是针对魅族MX5。MX5通过Camera.open()拿到的Camera对象不为null
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }
        if (mCamera != null) {
            mCamera.release();
        }

        return canUse;
    }


    AppUpdateProgressDialog appUpdateProgressDialog;

    private void updateapp()
    {
        Api.getApi().getversion()
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<VersionBean>(MainActivity.this, false) {
                    @Override
                    public void onSuccess(VersionBean versionBean) {
                        LogUtils.e("baseBean"+versionBean.toString());
                        if(compareVersion(BuildConfig.VERSION_NAME,versionBean.getData().getAndroidVersion())==-1)
                        {
                            appUpdateProgressDialog = new AppUpdateProgressDialog(MainActivity.this);
                            appUpdateProgressDialog.show();
                            Intent intent = new Intent(MainActivity.this, DownloadService.class);
                            intent.putExtra("url",versionBean.getData().getAndroidUrl());
                            intent.putExtra("receiver", new DownloadReceiver(new Handler(), appUpdateProgressDialog));
                            startService(intent);
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        Log.d("HomePageActivity", "version1Array=="+version1Array.length);
        Log.d("HomePageActivity", "version2Array=="+version2Array.length);
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        Log.d("HomePageActivity", "verTag2=2222="+version1Array[index]);
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }




}
