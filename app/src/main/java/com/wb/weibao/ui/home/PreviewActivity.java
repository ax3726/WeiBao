package com.wb.weibao.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.hikvision.open.hikvideoplayer.HikVideoPlayer;
import com.hikvision.open.hikvideoplayer.HikVideoPlayerCallback;
import com.hikvision.open.hikvideoplayer.HikVideoPlayerFactory;
import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityPreviewBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.CameraListBean;
import com.wb.weibao.utils.MyUtils;
import com.wb.weibao.utils.picker.common.LineConfig;
import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.picker.SinglePicker;

import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Environment.DIRECTORY_DCIM;

/**
 * 错误码开头：17是mgc或媒体取流SDK的错误，18是vod，19是dac
 */
public class PreviewActivity extends BaseActivity<BasePresenter,ActivityPreviewBinding> implements View.OnClickListener, HikVideoPlayerCallback, TextureView.SurfaceTextureListener {
    private static final String TAG = "PreviewActivity";
    private static String previewUri ;
    private String mUri;
    private HikVideoPlayer mPlayer;
    private boolean mSoundOpen = false;
    private boolean mRecording = false;
    private PlayerStatus mPlayerStatus = PlayerStatus.IDLE;//默认闲置

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preview;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return false;
    }

//    @Override
//    protected void initTitleBar() {
//        super.initTitleBar();
//        mTitleBarLayout.setTitle("视频监控");
//        mTitleBarLayout.getTitleView().setCompoundDrawables(null,null,getResources().getDrawable(R.drawable.bottom_jiantou),null);
//    }
    @Override
    protected void initData() {
        super.initData();
        mBinding.tvTitle.setText("选择视频监控");
        mBinding.start.setOnClickListener(this);
        mBinding.captureButton.setOnClickListener(this);
        mBinding.recordButton.setOnClickListener(this);
        mBinding.textureView.setSurfaceTextureListener(this);

        mPlayer = HikVideoPlayerFactory.provideHikVideoPlayer();
        mBinding.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getvideourl();
            }
        });
mBinding.llyLeft.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }

    private List<CameraListBean.DataBean.ListBean> mDataList=null;
    private int mProjectIndex=-1;
    public void getvideourl()
    {
        Api.getApi().getCameraList(""+MyApplication.getInstance().getUserData().getId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<CameraListBean>(PreviewActivity.this, false) {
                    @Override
                    public void onSuccess(CameraListBean cameraListBean) {
                        LogUtils.e("baseBean" + cameraListBean.getData().toString());
                        CameraListBean.DataBean data = cameraListBean.getData();
                        if (data != null) {
                            if (data.getList() != null && data.getList().size() > 0) {
                                mDataList=data.getList();

                                List<String> lists=new ArrayList<>();
                                for (CameraListBean.DataBean.ListBean bean:data.getList()) {
                                    lists.add(bean.getCameraName());
                                }
                                projectcameralist(lists.toArray(new String[lists.size()]));
                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start) {
            if (mProjectIndex != -1) {
                if (mPlayerStatus != PlayerStatus.SUCCESS && getPreviewUri()) {
                    startRealPlay(mBinding.textureView.getSurfaceTexture());
                    mBinding.start.setVisibility(View.GONE);
                }
            }else
            {
                showToast("请先选择预览的监控视频");
            }
        }
//        else if (view.getId() == R.id.stop) {
//            if (mPlayerStatus == PlayerStatus.SUCCESS) {
//                mPlayerStatus = PlayerStatus.IDLE;//释放这个窗口
//                mRecordFilePathText.setText(null);
//                progressBar.setVisibility(View.GONE);
//                playHintText.setVisibility(View.VISIBLE);
//                playHintText.setText("");
//                mPlayer.stopPlay();
//            }
//        }
         else if (view.getId() == R.id.capture_button) {

            executeCaptureEvent();
        } else if (view.getId() == R.id.record_button) {
            executeRecordEvent();
        }

//        else if (view.getId() == R.id.sound_button) {
//            executeSoundEvent();
//        }
    }


    /**
     * 执行抓图事件
     */
    private void executeCaptureEvent() {
        if (mPlayerStatus != PlayerStatus.SUCCESS) {
            ToastUtils.showShort("没有视频在播放");
        }

        //抓图
        if (mPlayer.capturePicture(MyUtils.getCaptureImagePath(this))) {
//            UniversalToast.makeText(PreviewActivity.this, "已保存到手机相册", UniversalToast.LENGTH_SHORT,UniversalToast.EMPHASIZE).setLeftIconRes(R.drawable.ic_view_error).show();

        }
    }


    /**
     * 执行录像事件
     */
    private void executeRecordEvent() {
        if (mPlayerStatus != PlayerStatus.SUCCESS) {
            ToastUtils.showShort("没有视频在播放");
        }


        if (!mRecording) {
            //开始录像
//            mRecordFilePathText.setText(null);
            String path=  MyUtils.getLocalRecordPath(this,mBinding.tvTitle.getText().toString());
            if (mPlayer.startRecord(path)) {
                ToastUtils.showShort("开始录像");
                mRecording = true;
                mBinding.recordButton.setBackgroundResource(R.drawable.ic_view_video2);
                mBinding.time.setVisibility(View.VISIBLE);
                startWatch();

            }
        } else {
            //关闭录像
            mPlayer.stopRecord();
            ToastUtils.showShort("关闭录像");
            mRecording = false;
            mBinding.recordButton.setBackgroundResource(R.drawable.ic_view_video);
            stopTimeShow();
            mBinding.time.setVisibility(View.GONE);
            MyApplication.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM))));
        }
    }

    private long mlCount = 0; //计时 次数
    private int  mSecRate = 10; // 10 ms 刷新一次
    private String timeShow = "";
    private Timer mTimer1;
    private TimerTask mTask1;

    //开始计时
    private void startWatch() {
        if (mTimer1 == null && mTask1 == null) {
            mTimer1 = new Timer();
            mTask1 = new TimerTask() {
                @Override
                public void run() {
                    Message message = mHandler.obtainMessage(1);
                    mHandler.sendMessage(message);
                }
            };
            mTimer1.schedule(mTask1, 0, mSecRate);  //10 ms 刷新一次
        }

    }


    /**
     * 计时器
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 1:
                    mlCount++;
                    judgeTimeShow(mlCount);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    //停止刷新显示
    private void stopTimeShow() {
        if (mTimer1 != null) {
            mTimer1.cancel();
            mTimer1 = null;
        }
        if (mTask1 != null) {
            mTask1.cancel();
            mTask1 = null;
        }
    }

    //判断显示
    private String  judgeTimeShow(long mlCount) {
        String str ="";
        long min = 0;
        long sec = 0;
        long mSec = 0;
        if (mlCount <=0) {
            str = "00:00:00";
        } else {
            sec = mlCount / (1000/mSecRate);  // 由毫秒 计算出 秒
            if (sec < 60) {
                mSec = mlCount % (1000/mSecRate);  //剩余下的 毫秒
            } else {
                min = sec / 60;    //由秒计算出 min
                if (min > 99) {
                    str = "99:59:59";
                    mBinding.time.setText(str);
                    return str;
                }
                sec = sec % 60;
                mSec = mlCount - (min * 60 * (1000/mSecRate)) - (sec * (1000/mSecRate));
            }

            str = judgeSingleNum(min) + ":"+ judgeSingleNum(sec) + ":"+ judgeSingleNum(mSec);
        }

        Log.i(TAG,"时间是 mlCount：" + mlCount + "\n"
                + "设置的时间是：" + str);
        mBinding.time.setText(str);
        return str;
    }

    //判断是不是要加上0
    private String judgeSingleNum(long mlCount) {
        String strData = "";
        if (mlCount < 10) {
            strData = "0" + mlCount;
        } else {
            strData = mlCount + "";
        }
        return strData;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
        mRunnable = null;
    }



    /**
     * 执行声音开关事件
     */
    private void executeSoundEvent() {
        if (mPlayerStatus != PlayerStatus.SUCCESS) {
            ToastUtils.showShort("没有视频在播放");
        }

//        if (!mSoundOpen) {
//            //打开声音
//            if (mPlayer.enableSound(true)) {
//                ToastUtils.showShort("声音开");
//                mSoundOpen = true;
//                soundButton.setText(R.string.sound_close);
//            }
//        } else {
//            //关闭声音
//            if (mPlayer.enableSound(false)) {
//                ToastUtils.showShort("声音关");
//                mSoundOpen = false;
//                soundButton.setText(R.string.sound_open);
//            }
//        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //TODO 注意:APP前后台切换时 SurfaceTextureListener可能在有某些 华为手机 上不会回调，例如：华为P20，所以我们在这里手动调用
        if (mBinding.textureView.isAvailable()) {
            Log.e(TAG, "onResume: onSurfaceTextureAvailable");
            onSurfaceTextureAvailable(mBinding.textureView.getSurfaceTexture(), mBinding.textureView.getWidth(), mBinding.textureView.getHeight());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO 注意:APP前后台切换时 SurfaceTextureListener可能在有某些 华为手机 上不会回调，例如：华为P20，所以我们在这里手动调用
        if (mBinding.textureView.isAvailable()) {
            Log.e(TAG, "onPause: onSurfaceTextureDestroyed");
            onSurfaceTextureDestroyed(mBinding.textureView.getSurfaceTexture());
        }

    }


    /**
     * 开始播放
     *
     * @param surface 渲染画面
     */
    private void startRealPlay(SurfaceTexture surface) {
        mPlayerStatus = PlayerStatus.LOADING;
        mBinding.progressBar.setVisibility(View.VISIBLE);
        mBinding.resultHintText.setVisibility(View.GONE);
        mPlayer.setSurfaceTexture(surface);
        //TODO 注意: startRealPlay() 方法会阻塞当前线程，需要在子线程中执行,建议使用RxJava
        new Thread(() -> {
            //TODO 注意: 不要通过判断 startRealPlay() 方法返回 true 来确定播放成功，播放成功会通过HikVideoPlayerCallback回调，startRealPlay() 方法返回 false 即代表 播放失败;
            if (!mPlayer.startRealPlay(mUri, PreviewActivity.this)) {
                onPlayerStatus(Status.FAILED, mPlayer.getLastError());
            }
        }).start();
    }


    /**
     * 播放结果回调
     *
     * @param status    共四种状态：SUCCESS（播放成功）、FAILED（播放失败）、EXCEPTION（取流异常）、FINISH（回放结束）
     * @param errorCode 错误码，只有 FAILED 和 EXCEPTION 才有值
     */
    @Override
    @WorkerThread
    public void onPlayerStatus(@NonNull Status status, int errorCode) {
        //TODO 注意: 由于 HikVideoPlayerCallback 是在子线程中进行回调的，所以一定要切换到主线程处理UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBinding.progressBar.setVisibility(View.GONE);
                switch (status) {
                    case SUCCESS:
                        //播放成功
                        mPlayerStatus = PlayerStatus.SUCCESS;
                        mBinding.resultHintText.setVisibility(View.GONE);
                        mBinding.textureView.setKeepScreenOn(true);//保持亮屏
                        break;
                    case FAILED:
                        //播放失败
                        mPlayerStatus = PlayerStatus.FAILED;
                        mBinding.resultHintText.setVisibility(View.VISIBLE);
                        mBinding.resultHintText.setText(MessageFormat.format("预览失败，错误码：{0}", Integer.toHexString(errorCode)));
                        break;
                    case EXCEPTION:
                        //取流异常
                        mPlayerStatus = PlayerStatus.EXCEPTION;
                        mPlayer.stopPlay();//TODO 注意:异常时关闭取流
                        mBinding.resultHintText.setVisibility(View.VISIBLE);
                        mBinding.resultHintText.setText(MessageFormat.format("取流发生异常，错误码：{0}", Integer.toHexString(errorCode)));
                        break;
                }
            }
        });
    }

    private boolean getPreviewUri() {
        mUri = previewUri;
        if (TextUtils.isEmpty(mUri)) {
//            reviewUriEdit.setError("URI不能为空");
            return false;
        }

        if (!mUri.contains("rtsp")) {
//            reviewUriEdit.setError("非法URI");
            return false;
        }

        return true;
    }


    /*************************TextureView.SurfaceTextureListener 接口的回调方法********************/
    //TODO 注意:APP前后台切换时 SurfaceTextureListener可能在有某些华为手机上不会回调，例如：华为P20，因此我们需要在Activity生命周期中手动调用回调方法
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (mPlayerStatus == PlayerStatus.STOPPING) {
            //恢复处于暂停播放状态的窗口
            startRealPlay(mBinding.textureView.getSurfaceTexture());
            Log.d(TAG, "onSurfaceTextureAvailable: startRealPlay");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mPlayerStatus == PlayerStatus.SUCCESS) {
            mPlayerStatus = PlayerStatus.STOPPING;//暂停播放，再次进入时恢复播放
            mPlayer.stopPlay();
            Log.d(TAG, "onSurfaceTextureDestroyed: stopPlay");
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


    @SuppressLint("ResourceAsColor")
    public void projectcameralist(String [] strs) {
        SinglePicker<String> picker = new SinglePicker<>(this,
                strs );
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(R.color.btn_cancel_color);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(Color.BLUE);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0x00000000);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFEEEEEE);
        picker.setSelectedIndex(7);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                mBinding.tvTitle.setText(item);
                mProjectIndex=index;
                mBinding.name.setText(item);
                Api.getApi().getCameraurl(""+MyApplication.getInstance().getUserData().getId(),""+mDataList.get(mProjectIndex).getId())
                        .compose(callbackOnIOToMainThread())
                        .subscribe(new BaseNetListener<BaseBean>(PreviewActivity.this, true) {
                            @Override
                            public void onSuccess(BaseBean baseBean) {
                                LogUtils.e("baseBean" + baseBean.getData().toString());
                                previewUri=baseBean.getData().toString();
                                if (mPlayerStatus != PlayerStatus.SUCCESS && getPreviewUri()) {
                                    startRealPlay(mBinding.textureView.getSurfaceTexture());
                                    mBinding.start.setVisibility(View.GONE);
                                    if (mRunnable == null) {
                                        mRunnable = new MyRunnable();
                                        mHandler.postDelayed(mRunnable, 0);
                                    }
                                }
                            }

                            @Override
                            public void onFail(String errMsg) {

                            }
                        });
            }
        });
        picker.show();
    }



    private MyRunnable mRunnable;

    private static class MyHandler extends Handler {
        private final WeakReference<PreviewActivity> mActivity;

        private MyHandler(PreviewActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            PreviewActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 5:
                        this.removeMessages(4);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private final MyHandler mHandlers = new MyHandler(this);



    /**
     * 获取时间
     *
     * @return 格式化后的时间
     */
    private static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(new Date());
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            mBinding.cameraTime.setText(getTime());
            mHandlers.postDelayed(this, 1000);
        }
    }







}
