package com.wb.weibao.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wb.weibao.R;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.databinding.ActivitySentriesBinding;
import com.wb.weibao.utils.Timeline.ImageLoad;
import com.wb.weibao.utils.Timeline.TestO;
import com.wb.weibao.utils.Timeline.TimelineFragment;
import com.wb.weibao.utils.Timeline.TimelineGroupType;
import com.wb.weibao.utils.Timeline.TimelineObject;
import com.wb.weibao.utils.Timeline.TimelineObjectClickListener;

import java.util.ArrayList;

public class SentriesActivity extends BaseActivity<BasePresenter,ActivitySentriesBinding> implements TimelineObjectClickListener {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_sentries;
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
        mTitleBarLayout.setTitle("查岗");
    }

    private TimelineFragment mFragment;
    @Override
    protected void initData() {
        super.initData();

        // instantiate the TimelineFragment
        mFragment = new TimelineFragment();

        ArrayList<TimelineObject> objs = loadDataInTimeline();

        //Set data
        mFragment.setData(objs, TimelineGroupType.MONTH);
        //Set configurations
        mFragment.addOnClickListener(this);
        loadFragment(mFragment);
    }

    private ArrayList<TimelineObject> loadDataInTimeline() {
        //Load the data in a list and sort it by times in milli
        ArrayList<TimelineObject> objs = new ArrayList<>();
        objs.add(new TestO("2019.01.28 15:45:20","未应答 10分钟内未应答"));
        objs.add(new TestO("2019.01.28 16:45:20","2019.01.28 16:45:20"));
        objs.add(new TestO("2019.01.28 17:45:20","2019.01.28 17:45:20"));
        objs.add(new TestO("2019.01.28 18:45:20","2019.01.28 18:45:20"));
        objs.add(new TestO("2019.01.28 19:45:20","2019.01.28 19:45:20"));
        objs.add(new TestO("2019.01.28 20:45:20","2019.01.28 20:45:20"));
        return objs;
    }

    private void loadFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.commit();
    }

    @Override
    public void onTimelineObjectClicked(TimelineObject timelineObject) {
        Toast.makeText(getApplicationContext(),"Clicked: "+timelineObject.getTimestamp(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimelineObjectLongClicked(TimelineObject timelineObject) {
        Toast.makeText(getApplicationContext(),"LongClicked: "+timelineObject.getTimestamp(),Toast.LENGTH_LONG).show();
    }
}
