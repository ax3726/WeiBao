package com.wb.weibao.ui.home;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.common.PlayNumService;
import com.wb.weibao.common.TimeTaskService;
import com.wb.weibao.databinding.FragmentHomeLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.ErrorEvent;
import com.wb.weibao.model.event.ProjectChangeEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.main.MainActivity;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by LiMing
 * Date 2019/2/15
 */
public class HomeFragment extends BaseFragment<BaseFragmentPresenter, FragmentHomeLayoutBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }
    private SpfUtils spfUtils;
    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.tvSign.setOnClickListener(this);
        mBinding.tvDayRecord.setOnClickListener(this);
        mBinding.tvHandover.setOnClickListener(this);
        mBinding.tvAddWeibao.setOnClickListener(this);
        mBinding.tvMyWeibao.setOnClickListener(this);
        mBinding.tvWarningRecord.setOnClickListener(this);
        mBinding.tvLookGang.setOnClickListener(this);
        mBinding.tvWeibaoOrder.setOnClickListener(this);
        mBinding.tvFireControl.setOnClickListener(this);
        mBinding.tvPeixun.setOnClickListener(this);
        mBinding.tvProject.setOnClickListener(this);
        mBinding.tvSy.setOnClickListener(this);
        mBinding.tvSw.setOnClickListener(this);
        mBinding.tvDq.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spfUtils = SpfUtils.getInstance(aty);
        String  str= spfUtils.getSpfString(SpfKey.IS_PUSH_PLAY);
        if(TextUtils.isEmpty(str))
        {
            str="ok";
            spfUtils.setSpfString(SpfKey.IS_PUSH_PLAY,str);
        }
        EventBus.getDefault().register(this);
        if (MyApplication.getInstance().getUserData().getType().equals("1")) {
            getProjectList();
        } else {
//                getProjectList();
            getProjectList2();
        }

        Api.getApi().getCameraurl(""+ MyApplication.getInstance().getUserData().getId(),"38")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(HomeFragment.this, false) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        LogUtils.e("baseBean" + baseBean.getData().toString());
//                        previewUri=baseBean.getData().toString();

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign://视频监控
                startActivity(PreviewActivity.class);
                break;
            case R.id.tv_day_record://日常维保记录
                startActivity(AddDayWeiBaoActivity.class);
                break;
            case R.id.tv_handover://查岗
                startActivity(SentriesActivity.class);
                break;
            case R.id.tv_add_weibao://发起维保
                startActivity(InitiateWeibaoActivity.class);
                break;
            case R.id.tv_my_weibao://我的维保
                startActivity(MySecurityActivity.class);
                break;
            case R.id.tv_warning_record://警报统计
                startActivity(StatisticsActivity.class);
                break;
            case R.id.tv_look_gang://消防微站
                startActivity(FireControlActivity.class);
                break;
            case R.id.tv_weibao_order://维保订单
                if (MyApplication.getInstance().getUserData().getType().equals("1")) {
                    startActivity(new Intent(aty, MySecurityActivity.class).putExtra("type", 1));
                }
                break;

            case R.id.tv_peixun://培训教育
                startActivity(TrainingEducationActivity.class);
                break;
            case R.id.tv_fire_control://值班签到
                startActivity(SignActivity.class);
                break;
            case R.id.tv_sy://交接班
                startActivity(ChangeShiftsActivity.class);
//                startActivity(new Intent(aty, NoDataActivity.class).putExtra("type", 1));
                break;
            case R.id.tv_sw://水位
                startActivity(new Intent(aty, NoDataActivity.class).putExtra("type", 2));
                break;
            case R.id.tv_dq://电气
                startActivity(new Intent(aty, NoDataActivity.class).putExtra("type", 3));
                break;
            case R.id.tv_project://选择项目
                if (MyApplication.getInstance().getUserData().getType().equals("1")) {
                    startActivity(ProjectListActivity.class);
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProjectChange(ProjectChangeEvent message) {
        mBinding.tvProject.setText(SpfUtils.getInstance(aty).getSpfString(SpfKey.INST_NAME));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(camera!=null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        EventBus.getDefault().unregister(this);
    }

    /**
     * 获取项目列表
     */
    private void getProjectList() {
        Api.getApi().getProject_list(MyApplication.getInstance().getUserData().getCompanyId(),
                "" + MyApplication.getInstance().getUserData().getId()).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (data.getList() != null && data.getList().size() > 0) {
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);
                                SpfUtils spfUtils = SpfUtils.getInstance(aty);
                                    spfUtils.setSpfString(SpfKey.INST_ID, String.valueOf(listBean.getId()));
                                    spfUtils.setSpfString(SpfKey.INST_NAME, listBean.getName());
                                    spfUtils.setSpfString(SpfKey.LatiTude, String.valueOf(listBean.getLatitude()));
                                    spfUtils.setSpfString(SpfKey.LongiTude, String.valueOf(listBean.getLongitude()));
                                    spfUtils.setSpfString(SpfKey.InstCode, String.valueOf(listBean.getInstCode()));
                                    MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                                    mBinding.tvProject.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
                                getErrorList();
                                        LogUtils.e( "ee==开始获取接口");
//                                        EventBus.getDefault().post(new ErrorEvent());


                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }


    /**
     * 获取项目列表
     */
    private void getProjectList2() {
        Api.getApi().getProject_list3(MyApplication.getInstance().getUserData().getCompanyId(), "" + MyApplication.getInstance().getUserData().getId(),
                MyApplication.getInstance().getUserData().getProjectId()).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (data.getList() != null && data.getList().size() > 0) {
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);
                                SpfUtils spfUtils = SpfUtils.getInstance(aty);
//                                if (!TextUtils.isEmpty(spfUtils.getSpfString(SpfKey.INST_ID))) {
//                                    MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
//                                    mBinding.tvProject.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
//                                } else {
                                spfUtils.setSpfString(SpfKey.INST_ID, String.valueOf(listBean.getId()));
                                spfUtils.setSpfString(SpfKey.INST_NAME, listBean.getName());
                                spfUtils.setSpfString(SpfKey.LatiTude, String.valueOf(listBean.getLatitude()));
                                spfUtils.setSpfString(SpfKey.LongiTude, String.valueOf(listBean.getLongitude()));
                                spfUtils.setSpfString(SpfKey.InstCode, String.valueOf(listBean.getInstCode()));
                                MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                                mBinding.tvProject.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
//                                }
                                getErrorList();
                                    LogUtils.e( "ee==开始获取接口");
//                                    EventBus.getDefault().post(new ErrorEvent());

                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }






    private int num = 0;
    MediaPlayer mMediaPlayer = null;

    /**
     * 获取预警列表
     */
    public void getErrorList() {
//        showToast("2323");
        Api.getApi().getRecordList("" + MyApplication.getInstance().getUserData().getId(), MyApplication.getInstance().getUserData().getCompanyId(), MyApplication.getInstance().getProjectId(), "1", "1", "37,53", "", 1, 15).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordListModel>(this, false) {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onSuccess(RecordListModel baseBean) {

                        RecordListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            List<RecordListModel.DataBean.ListBean> list = data.getList();
                            if (list != null && list.size() > 0) {
                                MyApplication.getInstance().setErrorlist("1");
                                geterrortoast();
                            }

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
    public void onPause() {
        super.onPause();
        if(camera!=null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }


    //    Camera camera = Camera.open();
//    Camera.Parameters p = camera.getParameters();
    Camera.Parameters p =null;
    Camera camera =null;
    private boolean mIsLight = false;
    //手电筒闪光开启
    private void processOnFlash() {

        if(camera!=null) {
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
        if(camera!=null) {
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







    public void geterrortoast()
    {
        //震动
        Vibrator vibrator = (Vibrator)aty.getSystemService(VIBRATOR_SERVICE);
        long[] patter = {1000, 1000, 2000, 50};
        vibrator.vibrate(patter, 0);


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
        //唤醒屏幕
        DemoUtils.wakeUpAndUnlock(MyApplication.getInstance());

        //直接创建，不需要设置setDataSource
        if (mMediaPlayer == null && "ok".equals(SpfUtils.getInstance(MyApplication.getInstance()).getSpfString(SpfKey.IS_PUSH_PLAY)) && !PlayNumService.getIntance().isIsPlay()) {

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
        }else
        {

            vibrator.cancel();
        }
    }





}
