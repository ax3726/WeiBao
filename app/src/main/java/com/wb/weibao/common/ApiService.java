package com.wb.weibao.common;


import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.model.earlywarning.ErrorListModel;
import com.wb.weibao.model.earlywarning.OrderListModel;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.record.RecordListModel;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * Created by lm on 2017/11/22.
 * Description:
 */

public interface ApiService {

    @GET()
    Flowable<ResponseBody> downloadFile(@Url String url);

    /**
     * 登录接口
     *
     * @param loginAccount
     * @param password
     * @return
     */
    @POST("login/login2")
    Flowable<LoginModel> getUserLogin(@Query("loginAccount") String loginAccount, @Query("password") String password);

    @POST("project/list")
    Flowable<ProjectListModel> getProject_list(@Query("instCode") String instCode, @Query("userId") String userId);


    /**
     * 预警列表接口
     *
     * @return
     */
    @POST("early/record/list")
    Flowable<ErrorListModel> getError_list(@Query("instCode") String instCode,
                                           @Query("userId") String userId,
                                           @Query("projectId") String projectId,
                                           @Query("status") int status,
                                           @Query("page") int page,
                                           @Query("size") int size);

    /**
     * 记录列表接口
     *
     * @return
     */
    @POST("early/record/list")
    Flowable<RecordListModel> getRecord_list(@Query("instCode") String instCode,
                                             @Query("userId") String userId,
                                             @Query("projectId") String projectId,
                                             @Query("page") int page,
                                             @Query("size") int size);

    /**
     * 订单列表接口
     *
     * @return
     */
    @POST("order/list")
    Flowable<OrderListModel> getOrderList(@Query("userId") String userId,
                                          @Query("page") int page,
                                          @Query("size") int size);


    /**
     * 新增订单接口
     *
     * @return
     */
    @POST("order/add")
    Flowable<BaseBean> addOrder(@Query("userId") String userId,
                                    @Query("instCode") String instCode,
                                    @Query("projectId") String projectId,
                                    @Query("type") int type,
                                    @Query("principalName") String principalName,
                                    @Query("principalPhone") String principalPhone,
                                    @Query("memo") String memo);


    /**
     * 修改密码接口
     *
     * @return
     */
    @POST("user/updatePwdByUserId")
    Flowable<BaseBean> getupdatePwd(@Query("userId") String userId,
                                          @Query("newPwd") String newPwd,
                                          @Query("oldPwd") String oldPwd);

    /**
     * 维保订单详情页接口
     *
     * @return
     */
    @POST("order/detail")
    Flowable<BaseBean> getorderDetail(@Query("userId") String userId,
                                    @Query("id") String id);


}
