package com.wb.weibao.ui.Login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.wb.weibao.BuildConfig;
import com.wb.weibao.base.BaseActivity;
import com.wb.weibao.base.BaseNetListener;
import com.wb.weibao.base.BasePresenter;
import com.lm.lib_common.utils.MD5Utils;
import com.lm.lib_common.utils.WorksSizeCheckUtil;
import com.wb.weibao.R;
import com.wb.weibao.common.Api;
import com.wb.weibao.common.MyApplication;
import com.wb.weibao.databinding.ActivityLoginBinding;
import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.model.LoginUserInfoBean;
import com.wb.weibao.model.VersionBean;
import com.wb.weibao.ui.main.MainActivity;
import com.wb.weibao.utils.CommonRecyclerAdapter;
import com.wb.weibao.utils.DemoUtils;
import com.wb.weibao.utils.SpfKey;
import com.wb.weibao.utils.SpfUtils;
import com.wb.weibao.utils.update.AppUpdateProgressDialog;
import com.wb.weibao.utils.update.AppUpdateProgressDialog2;
import com.wb.weibao.utils.update.DownloadReceiver;
import com.wb.weibao.utils.update.DownloadService;

import java.util.List;

public class LoginActivity extends BaseActivity<BasePresenter, ActivityLoginBinding> {


    private boolean flag = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    AppUpdateProgressDialog appUpdateProgressDialog;
    AppUpdateProgressDialog2 appUpdateProgressDialog2;

    @Override
    protected void initData() {
        super.initData();



        WorksSizeCheckUtil.textChangeListener listener = new WorksSizeCheckUtil.textChangeListener(mBinding.affirm);
        listener.addAllEditText(mBinding.inputPhone, mBinding.inputPassword);
        mBinding.pwdeye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    mBinding.pwdeye.setBackgroundResource(R.mipmap.ic_eye2);
                    mBinding.inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mBinding.inputPassword.setSelection(mBinding.inputPassword.getText().toString().trim().length());
                    return;
                }
                flag = true;
                mBinding.pwdeye.setBackgroundResource(R.mipmap.ic_eye1);
                mBinding.inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.inputPassword.setSelection(mBinding.inputPassword.getText().toString().trim().length());
            }
        });
        mBinding.affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin();
            }
        });


//
        mBinding.RememberPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RememberPasswordCheckBoxFlag = isChecked;
            }
        });
        SetText();

        try {
            MDbUtils = DbUtils.create(this, "Account");
            MDbUtils.configAllowTransaction(true);
            MDbUtils.configDebug(true);
            Listdatas = MDbUtils.findAll(LoginUserInfoBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }


        mBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.class);
            }
        });
        mBinding.forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ForgetPwdActivity.class);
            }
        });
        updateapp();
        mBinding.banben.setText("当前版本" + BuildConfig.VERSION_NAME);
    }


    private void updateapp() {
        Api.getApi3().getversion()
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<VersionBean>(LoginActivity.this, false) {
                    @Override
                    public void onSuccess(VersionBean versionBean) {
                        LogUtils.e("baseBean" + versionBean.toString());
                        if (compareVersion(BuildConfig.VERSION_NAME, versionBean.getData().getAndroidVersion()) == -1) {
                            appUpdateProgressDialog2 = new AppUpdateProgressDialog2(LoginActivity.this, versionBean.getData().getAndroidInfo(),versionBean.getData().getAndroidVersion());
                            appUpdateProgressDialog2.setOnItemUpdateClickListener(new AppUpdateProgressDialog2.onItemUpdateListener() {
                                @Override
                                public void onUpdateClick(View view) {
                                    appUpdateProgressDialog2.dismiss();
                                    appUpdateProgressDialog = new AppUpdateProgressDialog(LoginActivity.this);
                                    appUpdateProgressDialog.show();
                                    Intent intent = new Intent(LoginActivity.this, DownloadService.class);
                                    intent.putExtra("url", versionBean.getData().getAndroidUrl());
                                    intent.putExtra("receiver", new DownloadReceiver(new Handler(), appUpdateProgressDialog));
                                    startService(intent);
                                }
                            });
                            appUpdateProgressDialog2.show();
//
                        } else {
                            if (spfUtils.getSpfString(SpfKey.IS_LOGIN).length() > 0) {
                                if (spfUtils.getSpfString(SpfKey.LOGIN_NAME).length() > 0 && spfUtils.getSpfString(SpfKey.LOGIN_PASSWORD).length() > 0) {

                                    checkLogin();
                                }
                            }
                        }


                    }

                    @Override
                    public void onFail(String errMsg) {

                    }
                });

    }


    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        Log.d("HomePageActivity", "version1Array==" + version1Array.length);
        Log.d("HomePageActivity", "version2Array==" + version2Array.length);
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        Log.d("HomePageActivity", "verTag2=2222=" + version1Array[index]);
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    private void checkLogin() {
        String phone = mBinding.inputPhone.getText().toString().trim();
        String password = mBinding.inputPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showToast("用户名不能为空!");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空!");
            return;
        }
        Api.getApi3().getUserLogin(phone, MD5Utils.encryptMD5(password))
                .compose(callbackOnIOToMainThread())
                .subscribe(new BaseNetListener<LoginModel>(LoginActivity.this, true) {
                    @Override
                    public void onSuccess(LoginModel loginModel) {
                        MyApplication.getInstance().setUserData(loginModel.getData());
                        Log.e("====", loginModel.toString());

                        spfUtils.setSpfString(SpfKey.LOGIN_NAME, phone);
                        spfUtils.setSpfString(SpfKey.LOGIN_PASSWORD, RememberPasswordCheckBoxFlag ? password : "");
                        spfUtils.setSpfString(SpfKey.LOGIN_HEAD_URL, "");

                        /*************************************数据库账号密码处理*/
                        if (null != Listdatas) {
                            //相同的数据直接删除 后续保存插入
                            for (int i = 0; i < Listdatas.size(); i++) {
                                if (Listdatas.get(i).getUserName().equals(phone)) {
                                    try {
                                        MDbUtils.delete(Listdatas.get(i));
                                    } catch (DbException e) {
                                        e.printStackTrace();
                                    }
                                    refreshData();
                                }
                            }
                            //数据量最大3    超过删除默认 第一条数据
                            if (Listdatas.size() == 3) {
                                try {
                                    MDbUtils.delete(Listdatas.get(0));
                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                                refreshData();
                            }
                        }
                        LoginUserInfoBean infos = new LoginUserInfoBean();
                        infos.setUserName(phone);
                        infos.setHeadPicUrl("");
                        infos.setPassWord(RememberPasswordCheckBoxFlag ? password : "");            //判断是会否 点击了记录密码 决定 存在SqlLite密码

                        try {
                            MDbUtils.save(infos);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        refreshData();

                        spfUtils.setSpfString(SpfKey.IS_LOGIN, "true");
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }

                    @Override
                    public void onFail(String errMsg) {
                        mBinding.affirm.setClickable(true);
                    }
                });
        mBinding.affirm.setClickable(false);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initWedget();
    }

    /**
     * 初始化界面
     */
    // 下拉框选项数据源
    private List<LoginUserInfoBean> Listdatas;
    // 用来处理选中或者删除下拉项消息
    private DbUtils MDbUtils;
    private SpfUtils spfUtils;
    private boolean RememberPasswordCheckBoxFlag;
    int count = 0;

    private void initWedget() {
        mBinding.LoginSeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (Listdatas != null && Listdatas.size() != 0) {
                    if (count == 0) {
                        SettingVisibility(0);
                    } else {
                        SettingVisibility(1);
                    }
                }


            }
        });
        initPopupWindow();
    }

    private void SettingVisibility(int countcount) {
        if (countcount == 0) {
            mBinding.LoginRecyclerView.setVisibility(View.VISIBLE);
            mBinding.LoginSeleteBtn.setImageResource(R.drawable.top_jiantou);
            count = 1;
        } else if (countcount == 1) {
            mBinding.LoginRecyclerView.setVisibility(View.GONE);
            mBinding.LoginSeleteBtn.setImageResource(R.drawable.bottom_jiantou);
            count = 0;
        }
    }


    /**
     * 初始化下拉框
     */
    private void initPopupWindow() {
        if (null == Listdatas || Listdatas.size() == 0) {
            SettingVisibility(1);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.LoginRecyclerView.setLayoutManager(linearLayoutManager);
        CommonRecyclerAdapter commonRecyclerAdapter = new CommonRecyclerAdapter<LoginUserInfoBean>(LoginActivity.this, R.layout.activity_login_option_item, Listdatas) {
            @Override
            public void convert(ViewHolder holder, final LoginUserInfoBean item, final int position, boolean isScrolling) {
                TextView item_text = (TextView) holder.getView(R.id.item_text);
                ImageView delImage = (ImageView) holder.getView(R.id.delImage);
                item_text.setText(item.getUserName());

                item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBinding.inputPhone.setText(item.getUserName());
                        mBinding.inputPhone.setSelection(item.getUserName().length());
                        mBinding.inputPassword.setText(item.getPassWord());
                        mBinding.RememberPasswordCheckBox.setChecked(DemoUtils.isEmpty(item.getPassWord()) ? false : true);
                        SettingVisibility(1);
                    }
                });
                delImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /******************当删除  当前编辑框的  账号时应同时删除*/
                        if (Listdatas.get(position).getUserName().equals(spfUtils.getSpfString(SpfKey.LOGIN_NAME))) {
                            if (Listdatas.size() > 1) {
                                spfUtils.setSpfString(SpfKey.LOGIN_NAME, Listdatas.get(position == 0 ? 1 : 0).getUserName());
                                spfUtils.setSpfString(SpfKey.LOGIN_PASSWORD, Listdatas.get(position == 0 ? 1 : 0).getPassWord());
                            } else {
                                spfUtils.setSpfString(SpfKey.LOGIN_NAME, "");
                                spfUtils.setSpfString(SpfKey.LOGIN_PASSWORD, "");
                            }
                            SetText();
                        }
                        /******************/
                        try {
                            MDbUtils.delete(Listdatas.remove(position));
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        refreshData();
                        initPopupWindow();
                    }
                });
            }
        };
        mBinding.LoginRecyclerView.setAdapter(commonRecyclerAdapter);
    }

    private void refreshData() {
        try {
            Listdatas = MDbUtils.findAll(LoginUserInfoBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /*****************及时更新编辑框数据*/
    private void SetText() {
        spfUtils = SpfUtils.getInstance(LoginActivity.this);
        mBinding.inputPhone.setText(spfUtils.getSpfString(SpfKey.LOGIN_NAME));
        mBinding.inputPhone.setSelection(spfUtils.getSpfString(SpfKey.LOGIN_NAME).length());
        mBinding.inputPassword.setText(spfUtils.getSpfString(SpfKey.LOGIN_PASSWORD));

        mBinding.RememberPasswordCheckBox.setChecked(DemoUtils.isEmpty(spfUtils.getSpfString(SpfKey.LOGIN_PASSWORD)) ? false : true);
    }


}
