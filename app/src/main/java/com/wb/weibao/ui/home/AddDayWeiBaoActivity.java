package com.wb.weibao.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.lling.photopicker.PhotoPickerActivity;
import com.lm.lib_common.utils.WorksSizeCheckUtil;
import com.wb.weibao.R;
import com.wb.weibao.adapters.abslistview.CommonAdapter;
import com.wb.weibao.adapters.abslistview.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityAddDayWeiBaoBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.utils.DataUtils;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.picker.common.LineConfig;
import com.wb.weibao.utils.picker.listeners.OnItemPickListener;
import com.wb.weibao.utils.picker.picker.DatePicker;
import com.wb.weibao.utils.picker.picker.DateTimePicker;
import com.wb.weibao.utils.picker.picker.SinglePicker;
import com.wb.weibao.utils.picker.util.LogUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddDayWeiBaoActivity extends BaseActivity<BasePresenter, ActivityAddDayWeiBaoBinding> {

    public final int RequestCode = 1001;
    private List<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_day_wei_bao;
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
        mTitleBarLayout.setTitle("添加维保记录");
    }


    @Override
    protected void initData() {
        super.initData();



        mBinding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picker();
            }
        });
        mBinding.tvNextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextPicker();
            }
        });

        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        mBinding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProjectList();
            }
        });
        initAdapter();
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
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mBinding.gvBody.setAdapter(mAdapter);
    }


    private void Picker() {

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mBinding.tvTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                //.setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
//                //.setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                //.isDialog(true)//是否显示为对话框样式
                .build();

        pvTime.show();


    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        //"YYYY-MM-DD HH:MM:SS"        "yyyy-MM-dd"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void NextPicker() {

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mBinding.tvNextTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                //.setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
//                //.setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                //.isDialog(true)//是否显示为对话框样式
                .build();

        pvTime.show();
    }

    private ArrayList<String> mImageUUid = new ArrayList<>();

    private int mIndex = 0;

    public void submit() {

        mImageUUid.clear();
        mIndex = 0;
        if (mImgs.size() > 0) {
            loadImg(mImgs.get(mIndex));
        } else {
            addRecord();
        }
    }

    private void loadImg(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            // MultipartBody.Part  和后端约定好Key，这里的partName是用image

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), DemoUtils.getimageByte(str)));

            Api.getApi().upLoad(body)
                    .compose(callbackOnIOToMainThread()).subscribe(new BaseNetListener<BaseBean>(this, true) {
                @Override
                public void onSuccess(BaseBean baseBean) {
                    mImageUUid.add(baseBean.getData().toString());
                    mIndex++;
                    if (mIndex < mImgs.size()) {
                        loadImg(mImgs.get(mIndex));
                    } else {
                        addRecord();
                    }
                }

                @Override
                public void onFail(String errMsg) {
                    mBinding.affirm.setEnabled(true);
                }
            });
        } else {
            addRecord();
        }

    }

    public void addRecord() {
        String name = mBinding.etName.getText().toString().trim();
        String phone = mBinding.etPhone.getText().toString().trim();
        String content = mBinding.etContent.getText().toString().trim();

        String time = mBinding.tvTime.getText().toString().trim();
        String NextTime = mBinding.tvNextTime.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名!");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号!");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            showToast("请输入内容!");
            return;
        }
        if (TextUtils.isEmpty(time)) {
            showToast("请选择维保时间!");
            return;
        }
        if (TextUtils.isEmpty(time)) {
            showToast("请选择下次维保时间!");
            return;
        }
        if (mProjectIndex==-1) {
            showToast("请选择维修单位!");
            return;
        }
        mBinding.affirm.setEnabled(false);
        String str = DemoUtils.ListToString(mImageUUid, ";");


        Api.getApi().addRecord(MyApplication.getInstance().getUserData().getId() + "",
                mDataList.get(mProjectIndex).getId()+"", name, phone,DataUtils.formatDate(time+" 00:00:00","yyyy-MM-dd HH:mm:s") ,DataUtils.formatDate(NextTime+" 00:00:00","yyyy-MM-dd HH:mm:ss") +" 00:00:00", str, content)
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
                mAdapter.notifyDataSetChanged();

            }
        }
    }


    private List<ProjectListModel.DataBean.ListBean> mDataList=null;
    private int mProjectIndex=-1;

    /**
     * 获取项目列表
     */
    private void getProjectList() {
        Api.getApi().getProject_list2(MyApplication.getInstance().getUserData().getCompanyId(),
                "" + MyApplication.getInstance().getUserData().getId(),"1").compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<ProjectListModel>(this, true) {
                    @Override
                    public void onSuccess(ProjectListModel baseBean) {
                        ProjectListModel.DataBean data = baseBean.getData();
                        if (data != null) {
                            if (data.getList() != null && data.getList().size() > 0) {
                                mDataList=data.getList();

                                List<String> lists=new ArrayList<>();
                                for (ProjectListModel.DataBean.ListBean bean:data.getList()) {
                                    lists.add(bean.getName());
                                }
                                project(lists.toArray(new String[lists.size()]));
                            }

                        }

                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });
    }

    @SuppressLint("ResourceAsColor")
    public void project(String [] strs) {
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
                mBinding.tv1.setText(item);
                mProjectIndex=index;
            }
        });
        picker.show();
    }


}
