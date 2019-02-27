package com.wb.weibao.common;


import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.model.VersionBean;
import com.wb.weibao.model.earlywarning.ErrorListModel;
import com.wb.weibao.model.earlywarning.OrderListModel;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.DetailBean;
import com.wb.weibao.model.home.SignListModel;
import com.wb.weibao.model.record.RecordListModel;

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
                                           @Query("size") int size,
                                           @Query("subWarningType") String subWarningType);

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
                                             @Query("size") int size,
                                             @Query("subWarningType") String subWarningType);

    /**
     * 订单列表接口
     *
     * @return
     */
    @POST("order/list")
    Flowable<OrderListModel> getOrderList(@Query("userId") String userId,
                                          @Query("page") int page,
                                          @Query("size") int size,
                                          @Query("status") String status);


    /**
     * 新增订单接口
     *
     * @return
     */
    @POST("order/add")
    Flowable<BaseBean> addOrder(@Query("userId") String userId,
                                    @Query("instCode") String instCode,
                                    @Query("projectId") String projectId,
                                    @Query("type") String type,
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
    Flowable<DetailBean> getorderDetail(@Query("userId") String userId,
                                        @Query("id") String id);

    /**
     *  接受订单接口
     * @param userId
     * @param status
     * @return
     */
    @POST("order/update")
    Flowable<BaseBean> getorderUpdate(@Query("userId") String userId,
                                        @Query("status") String status,
                                        @Query("id") String id);

    /**
     * 维保反馈接口
     * @param userId
     * @param status
     * @param id
     * @param processingName
     * @param endAmount
     * @param returnMsg
     * @return
     */
    @POST("order/update")
    Flowable<BaseBean> getorderUpdateFankui(@Query("userId") String userId,
                                      @Query("status") String status,
                                      @Query("id") String id,
                                            @Query("processingName") String processingName,
                                            @Query("endAmount") String endAmount,
                                            @Query("returnMsg") String returnMsg);

    /**
     *  预警详情页面接口更新
     * @param userId
     * @param status
     * @return
     */
    @POST("early/record/update")
    Flowable<BaseBean> getearlyRecordUpdate(@Query("userId") String userId,
                                      @Query("status") String status,
                                      @Query("confirmUserId") String confirmUserId,
                                      @Query("id") String id);
    /**
     *  预警详情页面接口更新
     * @param userId
     * @param status
     * @return
     */
    @POST("early/record/update")
    Flowable<BaseBean> getearlyRecordUpdate2(@Query("userId") String userId,
                                            @Query("status") String status,
                                            @Query("closeUserId") String closeUserId,
                                            @Query("id") String id);

    /**
     * 联系我们接口
     * @param name
     * @param phone
     * @return
     */
    @POST("intention/add")
    Flowable<BaseBean> getintentionadd(@Query("name") String name,
                                             @Query("phone") String phone);

    /**
     * 版本更新接口
     * @return
     */
    @POST("version/version")
    Flowable<VersionBean> getversion();


    /**
     * 签到签退接口地址
     * @return
     */
    @POST("signup/list")
    Flowable<SignListModel> getSignList(@Query("userId") String userId);

    /**
     * 签到
     * @return
     */
    @POST("signup/add")
    Flowable<BaseBean> addSignIn(@Query("userId") String userId);

    /**
     * 签退
     * @return
     */
    @POST("signup/update")
    Flowable<BaseBean> addSignOut(@Query("userId") String userId);


    /**
     *交接班记录
     * @return
     */
    @POST("/earlywarn/handover/list")
    Flowable<BaseBean> getHandoverList(@Query("userId") String userId);

}
