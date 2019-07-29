package com.wb.weibao.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lling.photopicker.PhotoPickerActivity;
import com.wb.weibao.R;
import com.wb.weibao.adapters.abslistview.CommonAdapter;
import com.wb.weibao.adapters.abslistview.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityInitiateWeibaoBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.DeviceTypeModel;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;
import com.wb.weibao.utils.picker.common.LineConfig;
import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.picker.SinglePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 发起维保activity
 */
public class InitiateWeibaoActivity extends BaseActivity<BasePresenter, ActivityInitiateWeibaoBinding> {
    public final int RequestCode = 1001;
    private List<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_initiate_weibao;
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
        mTitleBarLayout.setTitle("维保");
    }


    private void initAdapter() {
        mImgs.add("");
        mAdapter = new CommonAdapter<String>(aty, R.layout.item_add_point_img_layout, mImgs) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                ImageView img = viewHolder.getView(R.id.img);
                ImageView img_del = viewHolder.getView(R.id.img_del);

                if (TextUtils.isEmpty(item)) {
                    img_del.setVisibility(View.GONE);
                    Glide.with(aty).load(R.mipmap.point_add_img_icon).into(img);
                } else {
                    img_del.setVisibility(View.VISIBLE);

                    Glide.with(aty).load(item).into(img);
                }
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(item)) {
                            Intent intent = new Intent(aty, PhotoPickerActivity.class);
                            intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, PhotoPickerActivity.MODE_SINGLE);
                            intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, true);
                            intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, 1);
                            startActivityForResult(intent, RequestCode);
                        }
                    }
                });
                img_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(mImgs.get(mImgs.size() - 1))) {
                            mImgs.add("");
                        }
                        mImgs.remove(position);
                        mImageUUid.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mBinding.gvBody.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        initAdapter();

        mBinding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDeviceList();
            }
        });
        mBinding.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFaultList();
            }
        });
        mBinding.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMaintenanceOrderTypeList();
            }
        });
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }


    /**
     * 设备
     */
    private void getDeviceList() {
        Api.getApi().getTypeList(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", "equipmentType")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<DeviceTypeModel>(this, true) {
                    @Override
                    public void onSuccess(DeviceTypeModel baseBean) {
                        List<DeviceTypeModel.DataBean> data = baseBean.getData();
                        if (data != null && data.size() > 0) {
                            List<String> lists = new ArrayList<>();
                            for (DeviceTypeModel.DataBean bean : data) {
                                lists.add(bean.getName());
                            }
                            getDeviceType(lists.toArray(new String[lists.size()]));
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 故障
     */
    private void getFaultList() {
        Api.getApi().getTypeList(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", "faultType")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<DeviceTypeModel>(this, true) {
                    @Override
                    public void onSuccess(DeviceTypeModel baseBean) {
                        List<DeviceTypeModel.DataBean> data = baseBean.getData();
                        if (data != null && data.size() > 0) {
                            List<String> lists = new ArrayList<>();
                            for (DeviceTypeModel.DataBean bean : data) {
                                lists.add(bean.getName());
                            }
                            getFaultType(lists.toArray(new String[lists.size()]));
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }

    /**
     * 维保
     */
    private void getMaintenanceOrderTypeList() {
        Api.getApi().getTypeList(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", "maintenanceOrderType")
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<DeviceTypeModel>(this, true) {
                    @Override
                    public void onSuccess(DeviceTypeModel baseBean) {
                        List<DeviceTypeModel.DataBean> data = baseBean.getData();
                        if (data != null && data.size() > 0) {
                            List<String> lists = new ArrayList<>();
                            for (DeviceTypeModel.DataBean bean : data) {
                                lists.add(bean.getName());
                            }
                            getMaintenanceOrderType(lists.toArray(new String[lists.size()]));
                        }
                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    @SuppressLint("ResourceAsColor")
    public void getDeviceType(String[] strs) {
        SinglePicker<String> picker = new SinglePicker<>(this,
                strs);
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
                mBinding.tv1.setText(item);
            }
        });
        picker.show();
    }

    @SuppressLint("ResourceAsColor")
    public void getFaultType(String[] strs) {
        SinglePicker<String> picker = new SinglePicker<>(this,
                strs);
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
                mBinding.tv2.setText(item);
            }
        });
        picker.show();
    }

    @SuppressLint("ResourceAsColor")
    public void getMaintenanceOrderType(String[] strs) {
        SinglePicker<String> picker = new SinglePicker<>(this,
                strs);
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
                mBinding.tv3.setText(item);
            }
        });
        picker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PhotoPickerActivity.RESULT_OK && requestCode == RequestCode) {
            ArrayList<String> result = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
            if (result != null && result.size() > 0) {
                mImgs.set(mImgs.size() - 1, result.get(0));
                if (mImgs.size() < 4) {
                    mImgs.add("");
                }
                loadImg(result.get(0));
                mAdapter.notifyDataSetChanged();

            }
        }
    }

    private ArrayList<String> mImageUUid = new ArrayList<>();

    private int mIndex = 0;

    public void submit() {

//        mImageUUid.clear();
//        mIndex = 0;
//        if (mImgs.size() > 0) {
//            loadImg(mImgs.get(mIndex));
//        } else {
            addRecord();
//        }
    }

    private void loadImg(String str) {
//        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            byte[] bytes = DemoUtils.getimageByte(str);
            // MultipartBody.Part  和后端约定好Key，这里的partName是用image
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), bytes));

            Api.getApi().upLoad(body)
                    .compose(callbackOnIOToMainThread()).subscribe(new BaseNetListener<BaseBean>(this, true) {
                @Override
                public void onSuccess(BaseBean baseBean) {
                    mImageUUid.add(baseBean.getData().toString());
//                    mIndex++;
//                    if (mIndex < mImgs.size()) {
//                        loadImg(mImgs.get(mIndex));
//                    } else {
//                        addRecord();
//                    }
                }

                @Override
                public void onFail(String errMsg) {

//                    mBinding.affirm.setEnabled(true);
                }
            });
//        } else {
//            addRecord();
//        }

    }

    public void addRecord() {
        String tv1 = mBinding.tv1.getText().toString().trim();
        String tv2 = mBinding.tv2.getText().toString().trim();
        String tv3 = mBinding.tv3.getText().toString().trim();
        String content = mBinding.etContent.getText().toString().trim();


        if (TextUtils.isEmpty(tv1)) {
            showToast("请选择设备!");
            return;
        }
        if (TextUtils.isEmpty(tv2)) {
            showToast("请选择故障类型!");
            return;
        }
        if (TextUtils.isEmpty(tv3)) {
            showToast("请选择维保类型!");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            showToast("请输入内容!");
            return;
        }
        mBinding.affirm.setEnabled(false);

        String str = DemoUtils.ListToString(mImageUUid, ";");
        Api.getApi().addWeiBao(MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "",
                MyApplication.getInstance().getUserData().getPrincipal().getInstCode()+"", tv3, tv1, tv2, SpfUtils.getInstance(aty).getSpfString(SpfKey.INST_ID), str, content)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("提交成功!");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(1000);
                                    finish();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }.start();
                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.affirm.setEnabled(true);
                    }
                });
    }

}
