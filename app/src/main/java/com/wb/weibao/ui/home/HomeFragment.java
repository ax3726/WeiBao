package com.wb.weibao.ui.home;

import android.content.Intent;
import android.view.View;

import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.R;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.common.TimeTaskService;
import com.wb.weibao.databinding.FragmentHomeLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.ProjectChangeEvent;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }



}
