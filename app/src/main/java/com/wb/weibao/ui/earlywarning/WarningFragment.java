package com.wb.weibao.ui.earlywarning;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.wb.weibao.R;
import com.wb.weibao.adapters.CommonPagerAdapter;
import com.wb.weibao.base.BaseFragment;
import com.wb.weibao.base.BaseFragmentPresenter;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.FragemntWarningBinding;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordDetailEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/8.
 */

public class WarningFragment extends BaseFragment<BaseFragmentPresenter, FragemntWarningBinding>  {

    private CommonPagerAdapter mMyPagerAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_warning;
    }

    @Override
    protected BaseFragmentPresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isTitleBar() {
        return true;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setLeftShow(false);
        mTitleBarLayout.setTitle("预警日志");
        mTitleBarLayout.setTextSize(20);
    }
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private FireFragment fireFragment;
    private AlarmFragment alarmFragment;
    private List<String> title = new ArrayList<>();
    private List<TextView> title_views = new ArrayList<>();



    @Override
    protected void initData() {
        super.initData();
        title.add("远程监控火警");
        title.add("九小场所火警");
        title.add("故障");
        title.add("用电异常");
        title.add("用水异常");
        title.add("拆除");
        title.add("其他");

        initFragment();
        count();
        EventBus.getDefault().register(this);


    }

    private void initFragment() {
      //  fireFragment= new FireFragment();
       // alarmFragment = new AlarmFragment();
        mFragments.add(new FireFragment());
        mFragments.add(new FireFragment());
        mFragments.add(new FireFragment());
        mFragments.add(new FireFragment());
        mFragments.add(new FireFragment());
        mFragments.add(new FireFragment());
        mFragments.add(new FireFragment());
      //  mFragments.add(alarmFragment);
        mMyPagerAdapter = new CommonPagerAdapter(getChildFragmentManager(), title, mFragments);
        mBinding.pager.setAdapter(mMyPagerAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.pager);
        setTitle();
        mBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /**
                 * 设置当前选中的Tab为特殊高亮样式。
                 */
                if (tab != null && tab.getCustomView() != null) {
                    TextView tab_layout_text = (TextView) tab.getCustomView().findViewById(R.id.tv_txt);
                    tab_layout_text.setTextColor(getResources().getColor(R.color.colorTheme));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /**
                 * 重置所有未选中的Tab颜色、字体、背景恢复常态(未选中状态)。
                 */
                if (tab != null && tab.getCustomView() != null) {
                    TextView tab_layout_text = (TextView) tab.getCustomView().findViewById(R.id.tv_txt);
                    tab_layout_text.setTextColor(getResources().getColor(R.color.color666666));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void setTitle() {
        for (int i = 0; i < title.size(); i++) {
            TabLayout.Tab tab = mBinding.tabLayout.getTabAt(i);
            if (tab != null) {
                View view=View.inflate(aty,R.layout.tab_layout_item,null);
                TextView textView = (TextView) view.findViewById(R.id.tv_txt);
                textView.setText(title.get(i));
                if (i==0) {
                    textView.setTextColor(getResources().getColor(R.color.colorTheme));
                }
                title_views.add(textView);
                tab.setCustomView(view);
            }
        }

    }

    public void toLoadData() {

        if (fireFragment != null) {
            fireFragment.toLoadData();
        }
        if (alarmFragment != null) {
            alarmFragment.toLoadData();
        }

     /*   if (mRecordFragment != null) {
            mRecordFragment.loadData();
        }*/
    }


    public  void count()
    {
        Api.getApi().getRecordcount(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"",MyApplication.getInstance().getProjectId())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<RecordCount>(WarningFragment.this, false) {
                    @Override
                    public void onSuccess(RecordCount baseBean) {

                        RecordCount.DataBean data = baseBean.getData();
                        if (data==null) {
                            return;
                        }
                        for (int i = 0; i <title.size() ; i++) {
                            int num=0;
                            switch (i) {
                                case 0://远程监控火警
                                    num=data.getRemoteMonitoringCountNum();
                                    break;
                                case 1://九小场所火警
                                    num=data.getNineSmallPlacesCountNum();
                                    break;
                                case 2://故障111
                                    num=0;
                                    break;
                                case 3://用电异常
                                    num=data.getElectricityFaultCountNum();
                                    break;
                                case 4://用水异常111
                                    num=0;
                                    break;
                                case 5://拆除
                                    num=0;
                                    break;
                                case 6://其他
                                    num=0;
                                    break;
                            }
                            if (num==0) {
                                title_views.get(i).setText(title.get(i));
                            } else if (num < 100) {
                                title_views.get(i).setText(title.get(i)+"("+num+")");
                            } else {
                                title_views.get(i).setText(title.get(i)+"(99+)");
                            }
                        }
                      /*      title_views.get(0).setText(mt);
                        LogUtils.e("baseBean" + baseBean.toString());
                        mBinding.tabTv1.setText("告警("+baseBean.getData().getAlarmWaitProccessNum()+")");
                       int sum=Integer.parseInt(String.valueOf(baseBean.getData().getFireWaitConfirmNum()))+Integer.parseInt(String.valueOf(baseBean.getData().getFireWaitProccessNum()));
                       mBinding.tabTv.setText("火警("+sum+")");*/
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refersh(RecordDetailEvent event) {
        count();

    }

}
