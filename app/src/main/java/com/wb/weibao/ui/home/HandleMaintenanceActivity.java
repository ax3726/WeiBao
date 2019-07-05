package com.wb.weibao.ui.home;

import android.content.Intent;
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
import com.wb.weibao.databinding.ActivityHandleMaintenanceBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.utils.DemoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HandleMaintenanceActivity extends BaseActivity<BasePresenter, ActivityHandleMaintenanceBinding> {

    public final int RequestCode = 1001;
    private List<String> mImgs = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    private String id = "";
    private String name = "";
    private String type = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handle_maintenance;
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
        mTitleBarLayout.setTitle("确定上报");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
//        mBinding.tvName.setText(TextUtils.isEmpty(name) ? "实际维保人:" : "实际维保人:" + name);
        mBinding.tvState.setText("4".equals(type) ? "维保状态:维保成功" : "维保状态:维保失败");
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
                        mImageUUid.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        };
        mBinding.gvBody.setAdapter(mAdapter);
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
        if (TextUtils.isEmpty(mBinding.etName.getText())) {
            showToast("请输入实际维保人");
            return;
        }
        String content = mBinding.etContent.getText().toString().trim();

        mBinding.affirm.setEnabled(false);

        String str = DemoUtils.ListToString(mImageUUid, ";");

        Api.getApi().handleWeiBao2(id, MyApplication.getInstance().getUserData().getPrincipal().getUserId() + "", type, str, content,mBinding.etName.getText().toString())
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<BaseBean>(this, true) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        mImageUUid.clear();
                        showToast("操作成功!");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                setResult(RESULT_OK);
                                finish();
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
