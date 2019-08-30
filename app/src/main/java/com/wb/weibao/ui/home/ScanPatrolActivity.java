package com.wb.weibao.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.lidroid.xutils.util.LogUtils;
import com.lling.photopicker.PhotoPickerActivity;
import com.wb.weibao.R;
import com.wb.weibao.adapters.abslistview.CommonAdapter;
import com.wb.weibao.adapters.abslistview.ViewHolder;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityScanPatrolBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.home.DeviceTypeModel;
import com.wb.weibao.model.record.RecordDetailEvent;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.LocationHelper;
import com.wb.weibao.view.GridViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ScanPatrolActivity extends BaseActivity<BasePresenter,ActivityScanPatrolBinding> {


    private ArrayList mList;
    private GridViewAdapter mAdapters;
    public final int RequestCode = 1001;
    private List<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;
    private double mLatitude = 0;
    private double mLongitude = 0;
    private String mLatitudes = "";
    private String mLongitudes= "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_patrol;
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

        mTitleBarLayout.setTitle("巡查处理");
    }

    @Override
    protected void initData() {
        super.initData();
        mList = new ArrayList<>();
        mList.add("异常");
        mList.add("正常");
        mAdapters = new GridViewAdapter(ScanPatrolActivity.this, mList);
        GridView gridView = (GridView) findViewById(R.id.gridView);

        gridView.setAdapter(mAdapters);
         mLatitudes=getIntent().getStringExtra("mLatitude");
        mLongitudes=getIntent().getStringExtra("mLongitude");
        //gridView的点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocationHelper.getInstance().startLocation(aty);
                //把点击的position传递到adapter里面去
                mAdapters.changeState(position);
                TextView textView = (TextView) view.findViewById(R.id.tv);

                 name = textView.getText().toString().trim();
            }
        });

        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        initAdapter();

        LocationHelper.getInstance().setILocationListener(new LocationHelper.ILocationListener() {
            @Override
            public void onLocationChanged(BDLocation location) {
                if (location != null) {
                    LogUtils.e("mLatitude==" + mLatitude);
                    LogUtils.e("mLongitude==" + mLongitude);
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();

                }
            }
        });
        LocationHelper.getInstance().startLocation(aty);

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





    private ArrayList<String> mImageUUid = new ArrayList<>();

    private int mIndex = 0;

    public void submit() {
        mBinding.affirm.setEnabled(false);
//        mImageUUid.clear();
//        mIndex = 0;
//        if (mImgs.size() > 0) {
//            loadImg(mImgs.get(mIndex));
//        }
        addRecord();
    }




    private void loadImg(String str) {
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
            }

            @Override
            public void onFail(String errMsg) {
            }
        });

    }
    String causename="";
    String name="";
    public void addRecord() {

        String content = mBinding.etContent.getText().toString().trim();

        if(name.isEmpty())
        {
            showToast("请选择处理方式");
            mBinding.affirm.setEnabled(true);
            return;
        }
        String str = DemoUtils.ListToString(mImageUUid, ";");
        if(name.equals("异常"))
        {

            if(mImageUUid.size()<=0)
            {
                showToast("请上传图片");
                mBinding.affirm.setEnabled(true);
                return;
            }
        }
        String type="";
        if(name.equals("异常")) {
           type="1";
        }else
            {
                type="2";
            }

            if(mLatitude==0||mLongitude==0)
            {
                getPatrolRecordAppAdd(mLatitudes,mLongitudes, content, str, type);
            }else
                {
                    getPatrolRecordAppAdd(""+mLatitude,""+mLongitude, content, str, type);
                }





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

    public void getPatrolRecordAppAdd(String Latitude,String Longitude,String content,String str,String type)
    {
        Api.getApi().getPatrolRecordAppAdd(getIntent().getStringExtra("patrolRecordId"), "" + getIntent().getStringExtra("code"), "" + Latitude, "" + Longitude, content, str, type)
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(ScanPatrolActivity.this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        showToast("提交成功");
                        setResult(RESULT_OK);
                        finish();

                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.affirm.setEnabled(true);
                    }
                });
    }


}
