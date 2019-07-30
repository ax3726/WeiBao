package com.wb.weibao.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.lm.lib_common.utils.Utils;
import com.wb.weibao.R;
import com.wb.weibao.adapters.recyclerview.CommonAdapter;
import com.wb.weibao.adapters.recyclerview.base.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivitySmartPatrolMapBinding;
import com.wb.weibao.databinding.ItemSmartPatrolMapLayoutBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.PatrolMapInfoModel;
import com.wb.weibao.utils.DemoUtils;

import java.util.ArrayList;
import java.util.List;

public class SmartPatrolMapActivity extends BaseActivity<BasePresenter, ActivitySmartPatrolMapBinding> {


    private CommonAdapter<PatrolMapInfoModel.DataBean.PatrolRecordPointsBean> mAdapter;
    private List<PatrolMapInfoModel.DataBean.PatrolRecordPointsBean>          mDataList = new ArrayList<>();
    private AMap                                                              aMap;
    private BottomSheetBehavior                                               bottomSheetBehavior;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_patrol_map;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getPatrolMapInfo() {
        Api.getApi().getPatrolMapInfo(getIntent().getStringExtra("patrolRecordId"))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<PatrolMapInfoModel>(this, true) {
                    @Override
                    public void onSuccess(PatrolMapInfoModel model) {
                        PatrolMapInfoModel.DataBean data = model.getData();
                        if (data != null) {
                            mDataList.clear();
                            if (data.getPatrolRecordPoints() != null && data.getPatrolRecordPoints().size() > 0) {
                                mDataList.addAll(data.getPatrolRecordPoints());

                                LatLng latLng_start = new LatLng(Double.valueOf(data.getPatrolRecordPoints().get(0).getLatitude()),
                                        Double.valueOf(data.getPatrolRecordPoints().get(0).getLongitude()));
                                Marker start_marker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_start_icon)));
                                start_marker.setPosition(latLng_start);
                                start_marker.setToTop();

                                LatLng latLng_end = new LatLng(Double.valueOf(data.getPatrolRecordPoints().get(data.getPatrolRecordPoints().size() - 1).getLatitude()),
                                        Double.valueOf(data.getPatrolRecordPoints().get(data.getPatrolRecordPoints().size() - 1).getLongitude()));
                                Marker end_marker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_end_icon)));
                                end_marker.setPosition(latLng_end);
                                end_marker.setToTop();

                                List<LatLng> latLngs = new ArrayList<LatLng>();
                                for (PatrolMapInfoModel.DataBean.PatrolRecordPointsBean bean : data.getPatrolRecordPoints()) {
                                    latLngs.add(new LatLng(Double.valueOf(bean.getLatitude()), Double.valueOf(bean.getLongitude())));
                                }
                               /* latLngs.add(new LatLng(30.202910899093403,120.28805598999026));
                                latLngs.add(new LatLng(30.191189902363895,120.1996503808594));
                                latLngs.add(new LatLng(30.217597266530422,120.24874553466799));*/
                                aMap.addPolyline(new PolylineOptions().
                                        addAll(latLngs).width(10).color(Color.parseColor("#047AFF")));
                                moveCamera(latLng_start);

                            }
                            mAdapter.notifyDataSetChanged();
                            mBinding.tvNo.setText(data.getUserId());
                            mBinding.tvNamePhone.setText(data.getUserName() + "/" + data.getUserPhone());
                            mBinding.tvStartTime.setText(DemoUtils.ConvertTimeFormat(data.getPatrolStartTime(),"yyyy-MM-dd HH:mm:ss"));
                            mBinding.tvEndTime.setText(DemoUtils.ConvertTimeFormat(data.getPatrolEndTime(),"yyyy-MM-dd HH:mm:ss"));
                            mBinding.tvProjectName.setText(data.getProjectName());


                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * //将地图移动到定位点
     *
     * @param latLng
     */
    private void moveCamera(LatLng latLng) {
        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //将地图移动到定位点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

    }

    @Override
    protected void initData() {
        super.initData();

        mAdapter = new CommonAdapter<PatrolMapInfoModel.DataBean.PatrolRecordPointsBean>(aty, R.layout.item_smart_patrol_map_layout, mDataList) {
            @Override
            protected void convert(ViewHolder holder, PatrolMapInfoModel.DataBean.PatrolRecordPointsBean item, int position) {
                ItemSmartPatrolMapLayoutBinding binding = holder.getBinding(ItemSmartPatrolMapLayoutBinding.class);
                binding.tvName.setText(item.getPatrolName());
                binding.tvType.setText(item.getPatrolType());
                binding.tvAddress.setText(item.getPatrolAddress());
                binding.tvJinWei.setText(item.getLongitude() + "," + item.getLatitude());
                binding.tvDemo.setText(item.getRemark());
                binding.tvOverTime.setText(DemoUtils.ConvertTimeFormat(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
                binding.llyImg.removeAllViews();
                if (!TextUtils.isEmpty(item.getPicturesOssKeys())) {
                    String[] imgs = item.getPicturesOssKeys().split(";");
                    for (String img : imgs) {
                        ImageView imageView = new ImageView(aty);
                        GlideUrl glideUrl = new GlideUrl(img, new LazyHeaders.Builder()
                                .addHeader("Cookie", "JSESSIONID=" + MyApplication.getInstance().getJSESSIONID())
                                .build());
                        Glide.with(aty).load(glideUrl).into(imageView);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) Utils.dip2px(aty, 24)
                                , (int) Utils.dip2px(aty, 24));
                        layoutParams.setMargins(0, 0, (int) Utils.dip2px(aty, 10), 0);
                        imageView.setLayoutParams(layoutParams);
                        binding.llyImg.addView(imageView);
                    }
                }


            }
        };
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rcBody.setAdapter(mAdapter);
        mBinding.rcBody.setNestedScrollingEnabled(false);
        getPatrolMapInfo();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//添加沉浸式状态栏
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
        mBinding.map.onCreate(savedInstanceState);// 此方法必须重写
        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.llyBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                mBinding.imgState.setSelected(newState != BottomSheetBehavior.STATE_COLLAPSED);
              /*  if (newState == BottomSheetBehavior.STATE_COLLAPSED) {//折叠

                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {//展开

                }*/
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        mBinding.imgState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetBehavior.setState(!mBinding.imgState.isSelected() ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
                mBinding.imgState.setSelected(!mBinding.imgState.isSelected());

            }
        });
        initMap();
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mBinding.map.getMap();
        }
        //去掉底部缩放健
        aMap.getUiSettings().setZoomControlsEnabled(false);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mBinding.map.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mBinding.map.onPause();

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBinding.map.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.map.onDestroy();
    }

}
