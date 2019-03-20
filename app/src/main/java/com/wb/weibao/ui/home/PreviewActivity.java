package com.wb.weibao.ui.home;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.wb.weibao.ui.Login.ForgetPwdActivity;
import com.wb.weibao.utils.MyUtils;

import java.io.FileNotFoundException;
import java.text.MessageFormat;

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
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("视频监控");
    }
    @Override
    protected void initData() {
        super.initData();

        mBinding.start.setOnClickListener(this);
        mBinding.captureButton.setOnClickListener(this);
        mBinding.recordButton.setOnClickListener(this);
        mBinding.textureView.setSurfaceTextureListener(this);
        Api.getApi().getCameraurl(""+MyApplication.getInstance().getUserData().getId(),"38")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(PreviewActivity.this, false) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        LogUtils.e("baseBean" + baseBean.getData().toString());
                        previewUri=baseBean.getData().toString();

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
        mPlayer = HikVideoPlayerFactory.provideHikVideoPlayer();
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start) {
            if (mPlayerStatus != PlayerStatus.SUCCESS && getPreviewUri()) {
                startRealPlay(mBinding.textureView.getSurfaceTexture());
                mBinding.start.setVisibility(View.GONE);
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
            ToastUtils.showShort("以保存到手机相册");

        }
    }

    /**
     * 执行录像事件
     */
    private void executeRecordEvent() {
        if (mPlayerStatus != PlayerStatus.SUCCESS) {
            ToastUtils.showShort("没有视频在播放");
        }
        String path=  MyUtils.getLocalRecordPath(this);
        if (!mRecording) {
            //开始录像
//            mRecordFilePathText.setText(null);

            if (mPlayer.startRecord(path)) {
                ToastUtils.showShort("开始录像");
                mRecording = true;
                mBinding.recordButton.setBackgroundResource(R.drawable.ic_view_video2);
//                recordButton.setText(R.string.close_record);
//                mRecordFilePathText.setText(MessageFormat.format("当前本地录像路径: {0}", path));
            }
        } else {
            //关闭录像
            mPlayer.stopRecord();
            ToastUtils.showShort("关闭录像");
            mBinding.recordButton.setBackgroundResource(R.drawable.ic_view_video);
            mRecording = false;


            insertVideoToMediaStore(path);

//            recordButton.setText(R.string.start_record);
        }
    }

    public  void insertVideoToMediaStore( String filePath) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA,filePath);
        // video/*
        values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
        getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
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




}
