package com.wb.weibao.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.wb.weibao.model.PatrolUserListBean;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.ErrorEvent;
import com.wb.weibao.model.event.ProjectChangeEvent;
import com.wb.weibao.model.home.HomePageStatisticsBean;
import com.wb.weibao.model.home.PatrolEndStatusBean;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.model.record.RecordListModel;
import com.wb.weibao.ui.main.MainActivity;
import com.wb.weibao.utils.DataUtils;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;
import com.wb.weibao.utils.dialog.MyDialogListener;
import com.wb.weibao.utils.dialog.StytledDialog;
import com.wb.weibao.view.MyAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        mBinding.tv01.setOnClickListener(this);
        mBinding.tv02.setOnClickListener(this);
        mBinding.tv03.setOnClickListener(this);
        mBinding.tv04.setOnClickListener(this);
        mBinding.tv05.setOnClickListener(this);
        mBinding.tv06.setOnClickListener(this);
        mBinding.tv07.setOnClickListener(this);
        mBinding.tv08.setOnClickListener(this);
        mBinding.tv09.setOnClickListener(this);
        mBinding.tv10.setOnClickListener(this);
        mBinding.tv11.setOnClickListener(this);
        mBinding.tvProject.setOnClickListener(this);
        mBinding.lookDetails.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        super.initData();
        spfUtils = SpfUtils.getInstance(aty);
        String str = spfUtils.getSpfString(SpfKey.IS_PUSH_PLAY);
        if (TextUtils.isEmpty(str)) {
            str = "ok";
            spfUtils.setSpfString(SpfKey.IS_PUSH_PLAY, str);
        }
        EventBus.getDefault().register(this);
        if (MyApplication.getInstance().getUserData().getPrincipal().getType().equals("1")) {
            getProjectList();
        } else {
//                getProjectList();
            getProjectList2();
        }

        getPatrolEndStatus();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.look_details://查看详情
                MainActivity aty = (MainActivity) this.aty;
                if (aty != null) {
                    aty.toChange(1);
                }
                break;

            case R.id.tv_01://视频监控
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(PreviewActivity.class);
                break;
            case R.id.tv_02://查岗
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(SentriesActivity.class);
                break;
            case R.id.tv_03://日常维保记录

                Intent intent=new Intent(getActivity(),WeiBaoRecordActivity.class);
                intent.putExtra("ProjectName",mBinding.tvProject.getText());
                startActivity(intent);
                break;
            case R.id.tv_04://智慧巡查
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
             startActivity(SmartPatrolActivity.class);
                break;
            case R.id.tv_05://电气
//                startActivity(new Intent(this.aty, NoDataActivity.class).putExtra("type", 3));
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(SmartlectorMonitoringActivity.class);
                break;
            case R.id.tv_06://水位
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(SmartwtaerActivity.class);
                break;
            case R.id.tv_07://消防微站
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(FireControlActivity.class);
                break;
            case R.id.tv_08://预警统计
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(StatisticsActivity.class);
                break;
            case R.id.tv_09://关联服务
                startActivity(LinkedServiceActivity.class);
                break;
            case R.id.tv_10://培训教育
                startActivity(TrainingEducationActivity.class);
                break;
            case R.id.tv_11://更多功能
                if (mBinding.tvProject.getText().equals("全部项目")) {
                    showToast("请选择项目单位");
                    return;
                }
                startActivity(MoreActivity.class);
                break;
            case R.id.tv_project://选择项目
                if (MyApplication.getInstance().getUserData().getPrincipal().getType().equals("1")) {
                    startActivity(ProjectListActivity.class);
                }
                break;
//            case R.id.tv_add_weibao://发起维保
//                if (mBinding.tvProject.getText().equals("全部项目")) {
//                    showToast("请选择项目单位");
//                    return;
//                }
//                startActivity(InitiateWeibaoActivity.class);
//                break;
//            case R.id.tv_my_weibao://我的维保
//                if (mBinding.tvProject.getText().equals("全部项目")) {
//                    showToast("请选择项目单位");
//                    return;
//                }
//                startActivity(MySecurityActivity.class);
//                break;
//            case R.id.tv_warning_record://警报统计
//                if (mBinding.tvProject.getText().equals("全部项目")) {
//                    showToast("请选择项目单位");
//                    return;
//                }
//                startActivity(StatisticsActivity.class);
//                break;
//
//            case R.id.tv_weibao_order://维保订单
//
//                if (MyApplication.getInstance().getUserData().getPrincipal().getType().equals("1")) {
//                    startActivity(new Intent(aty, MySecurityActivity.class).putExtra("type", 1));
//                }
//                break;
//
//            case R.id.tv_peixun://培训教育
//                startActivity(TrainingEducationActivity.class);
//                break;
//            case R.id.tv_fire_control://值班签到
//                if (mBinding.tvProject.getText().equals("全部项目")) {
//                    showToast("请选择项目单位");
//                    return;
//                }
//                startActivity(SignActivity.class);
//                break;
//            case R.id.tv_sy://交接班
//                if (mBinding.tvProject.getText().equals("全部项目")) {
//                    showToast("请选择项目单位");
//                    return;
//                }
//                startActivity(ChangeShiftsActivity.class);
//                startActivity(new Intent(aty, NoDataActivity.class).putExtra("type", 1));
//                break;
//

        }






    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProjectChange(ProjectChangeEvent message) {
        mBinding.tvProject.setText(SpfUtils.getInstance(aty).getSpfString(SpfKey.INST_NAME));
        getHomePageStatistics();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (camera != null) {
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
        Api.getApi().getProject_list(MyApplication.getInstance().getUserData().getPrincipal().getInstCode() + "",
                "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId()).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (data.getList() != null && data.getList().size() > 0) {
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);
                                SpfUtils                           spfUtils = SpfUtils.getInstance(aty);
                                spfUtils.setSpfString(SpfKey.INST_ID, String.valueOf(""));
                                spfUtils.setSpfString(SpfKey.INST_NAME, "全部项目");
//                                    spfUtils.setSpfString(SpfKey.LatiTude, String.valueOf(listBean.getLatitude()));
//                                    spfUtils.setSpfString(SpfKey.LongiTude, String.valueOf(listBean.getLongitude()));
//                                    spfUtils.setSpfString(SpfKey.InstCode, String.valueOf(listBean.getInstCode()));
                                MyApplication.getInstance().setProjectId(spfUtils.getSpfString(SpfKey.INST_ID));
                                mBinding.tvProject.setText(spfUtils.getSpfString(SpfKey.INST_NAME));
//                                getErrorList();
                                LogUtils.e("ee==开始获取接口");
                                EventBus.getDefault().post(new ErrorEvent());
                                getHomePageStatistics();

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
        Api.getApi().getProject_list3(MyApplication.getInstance().getUserData().getPrincipal().getInstCode() + "", "" + MyApplication.getInstance().getUserData().getPrincipal().getUserId(),
                MyApplication.getInstance().getUserData().getPrincipal().getProjectId()).compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, false) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (data.getList() != null && data.getList().size() > 0) {
                                ProjectListModel.DataBean.ListBean listBean = data.getList().get(0);
                                SpfUtils                           spfUtils = SpfUtils.getInstance(aty);
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
//                                getErrorList();
                                LogUtils.e("ee==开始获取接口");
                                EventBus.getDefault().post(new ErrorEvent());
                                getHomePageStatistics();
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
//
        Api.getApi().getRecordcount(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", MyApplication.getInstance().getUserData().getPrincipal().getInstCode() + "", MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordCount>(this, false) {
                    @Override
                    public void onSuccess(RecordCount baseBean) {
                        LogUtils.e("baseBean" + baseBean.toString());
                        if((baseBean.getData().getRemoteMonitoringCountNum()+baseBean.getData().getNineSmallPlacesCountNum())>0)
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


    //获取统计
    public void getHomePageStatistics() {
        Api.getApi().getHomePageStatistics("" + MyApplication.getInstance().getUserData().getPrincipal().getInstCode(), MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<HomePageStatisticsBean>(HomeFragment.this, false) {
                    @Override
                    public void onSuccess(HomePageStatisticsBean baseBean) {
                        LogUtils.e("baseBean" + baseBean.getData().toString());
//                        previewUri=baseBean.getData().toString();
                        mBinding.todayAlarmsNumber.setText("" + baseBean.getData().getTodayAlarmsNumber());
                        mBinding.historyToBeConfirmed.setText("历史待确认  " + baseBean.getData().getHistoryToBeConfirmed() + "    ");
                        mBinding.historyToBeProcessed.setText("    历史待处理  " + baseBean.getData().getHistoryToBeProcessed());

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    /**
     * 查询是否结束
     */
    private void getPatrolEndStatus() {
        Api.getApi().gtPatrolEndStatus()
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<PatrolEndStatusBean>(this, false) {
                    @Override
                    public void onSuccess(PatrolEndStatusBean baseBean) {
                       if(baseBean.getData().isIsEnd()==false)
                       {
                           new MyAlertDialog(aty).builder().setTitle("提示")
                                   .setMsg("您还未结束巡查，是否继续巡查？").setNegativeButton("结束巡查", new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   getEndPatrol(baseBean.getData().getPatrolRecordId());
                               }
                           }).setPositiveButton("继续巡查", new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {

                                   startActivity(new Intent(aty, SmartPatrolRecordActivity.class).putExtra("patrolRecordId", baseBean.getData().getPatrolRecordId()));

                               }
                           }).show();
                       }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    private void getEndPatrol(String id) {
        Api.getApi().getEndPatrol(id,"")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, false) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }





    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer2;


    @Override
    public void onPause() {
        super.onPause();
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }


    //    Camera camera = Camera.open();
//    Camera.Parameters p = camera.getParameters();
    Camera.Parameters p      = null;
    Camera            camera = null;
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
        //震动
        Vibrator vibrator = (Vibrator) aty.getSystemService(VIBRATOR_SERVICE);

        //唤醒屏幕
//        DemoUtils.wakeUpAndUnlock(MyApplication.getInstance());

        //直接创建，不需要设置setDataSource
        if (mMediaPlayer == null && "ok".equals(SpfUtils.getInstance(MyApplication.getInstance()).getSpfString(SpfKey.IS_PUSH_PLAY)) && !PlayNumService.getIntance().isIsPlay()) {


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
        boolean canUse  = true;
        Camera  mCamera = null;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
