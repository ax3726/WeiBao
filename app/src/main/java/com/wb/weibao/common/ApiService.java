package com.wb.weibao.common;


import com.wb.weibao.model.BaseBean;
import com.wb.weibao.model.LoginModel;
import com.wb.weibao.model.PatrolUserListBean;
import com.wb.weibao.model.PermissionListBean;
import com.wb.weibao.model.VersionBean;
import com.wb.weibao.model.earlywarning.ErrorListModel;
import com.wb.weibao.model.earlywarning.OrderListModel;
import com.wb.weibao.model.earlywarning.ProjectListModel;
import com.wb.weibao.model.event.DetailBean;
import com.wb.weibao.model.home.CameraListBean;
import com.wb.weibao.model.home.CheckListbean;
import com.wb.weibao.model.home.DeviceTypeModel;
import com.wb.weibao.model.home.FrieControlModel;
import com.wb.weibao.model.home.Handoverbean;
import com.wb.weibao.model.home.HomePageStatisticsBean;
import com.wb.weibao.model.home.MaintenanceListModel;
import com.wb.weibao.model.home.MaintenanceRecordDetailBean;
import com.wb.weibao.model.home.PatrolEndStatusBean;
import com.wb.weibao.model.home.PatrolMapInfoModel;
import com.wb.weibao.model.home.PatrolPointListBean;
import com.wb.weibao.model.home.ProjectDetailbean;
import com.wb.weibao.model.home.RecordListAppBean;
import com.wb.weibao.model.home.SecurityInfoModel;
import com.wb.weibao.model.home.SignListModel;
import com.wb.weibao.model.home.SmartElectorBean;
import com.wb.weibao.model.home.SmartElectorDetailBean;
import com.wb.weibao.model.home.SmartPatrolBean;
import com.wb.weibao.model.home.StatisticsModel;
import com.wb.weibao.model.home.WaterListModel;
import com.wb.weibao.model.home.WaterModel;
import com.wb.weibao.model.record.EventReportListbean;
import com.wb.weibao.model.record.RecordCount;
import com.wb.weibao.model.record.RecordListModel;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @POST("project/list")
    Flowable<ProjectListModel> getProject_listserach(@Query("instCode") String instCode, @Query("userId") String userId, @Query("name") String name);

    /**
     * @param instCode
     * @param userId
     * @param projectId
     * @return
     */
    @POST("project/list")
    Flowable<ProjectListModel> getProject_list3(@Query("instCode") String instCode, @Query("userId") String userId, @Query("projectId") String projectId);


    @POST("project/list")
    Flowable<ProjectListModel> getProject_list2(@Query("instCode") String instCode, @Query("userId") String userId, @Query("isMaintenance") String isMaintenance);

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
     * 接受订单接口
     *
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
     *
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
     * 预警详情页面接口更新
     *
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
     * 预警详情页面接口更新
     *
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
     *
     * @param name
     * @param phone
     * @return
     */
    @POST("intention/add")
    Flowable<BaseBean> getintentionadd(@Query("name") String name,
                                       @Query("phone") String phone);

    /**
     * 版本更新接口
     *
     * @return
     */
    @POST("version/version")
    Flowable<VersionBean> getversion();


    /**
     * 忘记密码
     *
     * @return
     */
    @POST("login/phoneCode")
    Flowable<BaseBean> getphoneCode(@Query("phoneNo") String phoneNo);


    /**
     * 更新密码
     *
     * @param phoneNo
     * @param code
     * @param password
     * @return
     */
    @POST("login/updatePassword")
    Flowable<BaseBean> getupdatePassword(@Query("phoneNo") String phoneNo, @Query("code") String code, @Query("password") String password);


    /**
     * 预警模块
     *
     * @param userId
     * @param instCode
     * @param projectId
     * @param type
     * @param status
     * @param subWarningType
     * @param warnFlag
     * @param page
     * @param size
     * @return
     */
    @POST("early/record/list1")
    Flowable<RecordListModel> getRecordList(@Query("userId") String userId, @Query("instCode") String instCode, @Query("projectId") String projectId, @Query("type") String type, @Query("status") String status, @Query("subWarningType") String subWarningType, @Query("warnFlag") String warnFlag, @Query("page") int page, @Query("size") int size);


    /* 签到签退接口地址
     *
     * @return
     */
    @POST("signup/list")
    Flowable<SignListModel> getSignList(@Query("userId") String userId);

    /**
     * 检查状态
     *
     * @return
     */
    @POST("signup/now")
    Flowable<BaseBean> checkSign(@Query("userId") String userId);

    /**
     * 签到
     *
     * @return
     */
    @POST("signup/add")
    Flowable<BaseBean> addSignIn(@Query("userId") String userId);


    /**
     * 签退
     *
     * @return
     */
    @POST("signup/update")
    Flowable<BaseBean> addSignOut(@Query("userId") String userId);

    /**
     * 获取项目列表接口
     *
     * @return
     */
    @POST("project/list")
    Flowable<BaseBean> getProjectList(@Query("userId") String userId);

    /**
     * 我的维保
     *
     * @return
     */
    @POST("order/list")
    Flowable<MaintenanceListModel> getMyWeiBao(@Query("userId") String userId);


    /**
     * 我的维保
     *
     * @return
     */
    @POST("order/list")
    Flowable<MaintenanceListModel> getMyWeiBao2(@Query("userId") String userId, @Query("instCode") String instCode, @Query("projectId") String projectId);


    /**
     * 维保详情
     *
     * @return
     */
    @POST("order/detail")
    Flowable<SecurityInfoModel> getMyWeiBaoInfo(@Query("id") String id, @Query("userId") String userId);


    /**
     * 维保详情
     *
     * @return
     */
    @POST("project/detail")
    Flowable<ProjectDetailbean> getMyWeiBaoInfodetail(@Query("id") String id, @Query("userId") String userId);


    /**
     * 消防微站
     *
     * @return
     */
    @POST("project/station/list")
    Flowable<FrieControlModel> getFireControl(@Query("userId") String userId, @Query("projectId") String projecId);

    /**
     * 维保取消
     *
     * @return
     */
    @POST("/earlywarn/order/handle")
    Flowable<BaseBean> handleWeiBao(@Query("id") String id, @Query("userId") String userId, @Query("status") String status);

    /**
     * 维保取消
     *
     * @return
     */
    @POST("/earlywarn/order/handle")
    Flowable<BaseBean> handleWeiBao1(@Query("id") String id, @Query("userId") String userId, @Query("status") String status,
                                     @Query("processingOssKeys") String processingOssKeys, @Query("processingRet") String processingRet);


    /**
     * 维保上报
     *
     * @return
     */
    @POST("/earlywarn/order/handle")
    Flowable<BaseBean> handleWeiBao2(@Query("id") String id, @Query("userId") String userId, @Query("status") String status,
                                     @Query("processingOssKeys") String processingOssKeys, @Query("processingRet") String processingRet, @Query("processingName") String processingName);


    /**
     * 交接班记录
     *
     * @return
     */
    @POST("/earlywarn/handover/list")
    Flowable<Handoverbean> getHandoverList(@Query("userId") String userId);

    /**
     * 设备：equipmentType， 故障：faultType 维保：maintenanceOrderType
     *
     * @return
     */
    @POST("code/library/list")
    Flowable<DeviceTypeModel> getTypeList(@Query("userId") String userId, @Query("code") String code);

    /**
     * 设备：equipmentType， 故障：faultType 维保：maintenanceOrderType
     *
     * @return
     */
    @POST("code/library/getName")
    Flowable<BaseBean> getTypeListname(@Query("userId") String userId, @Query("codeValue") String codeValue, @Query("code") String code);


    /**
     * 新增维保记录
     *
     * @param userId
     * @param projectId
     * @param contractName
     * @param contractPhone
     * @param maintenanceDate
     * @param maintenanceNextDate
     * @param picturesOssKeys
     * @param maintenanceContent
     * @return
     */
    @POST("/earlywarn/maintenance/record/add")
    Flowable<BaseBean> addRecord(@Query("userId") String userId, @Query("projectId") String projectId, @Query("contractName") String contractName
            , @Query("contractPhone") String contractPhone, @Query("maintenanceDate") String maintenanceDate, @Query("maintenanceNextDate") String maintenanceNextDate
            , @Query("picturesOssKeys") String picturesOssKeys, @Query("maintenanceContent") String maintenanceContent, @Query("coverProjectId") String coverProjectId, @Query("coverProjectName") String coverProjectName);

    // 上传图片
    @Multipart
    @POST("/earlywarn/oss/upload")
    Flowable<BaseBean> upLoad(@Part MultipartBody.Part file);

    /**
     * 预警日志记录数量查询
     *
     * @param userId
     * @param instCode
     * @param projecId
     * @return
     */
    @POST("/earlywarn/early/record/count")
    Flowable<RecordCount> getRecordcount(@Query("userId") String userId, @Query("instCode") String instCode, @Query("projectId") String projecId);


    /**
     * 预警处理上报接口
     *
     * @param userId
     * @param eventType
     * @param cause
     * @param isFault
     * @param detailReasons
     * @param picturesOssKeys
     * @param earlyRecordId
     * @return
     */
    @POST("/earlywarn/early/event/report/add")
    Flowable<BaseBean> getReportadd(@Query("userId") String userId, @Query("eventType") String eventType, @Query("cause") String cause, @Query("isFault") String isFault, @Query("detailReasons") String detailReasons, @Query("picturesOssKeys") String picturesOssKeys, @Query("earlyRecordId") String earlyRecordId);


    @POST("/earlywarn/early/event/report/add")
    Flowable<BaseBean> getReportadd2(@Query("createUserId") String createUserId, @Query("eventType") String eventType, @Query("cause") String cause, @Query("causeName") String causeName, @Query("isFault") String isFault, @Query("detailReasons") String detailReasons, @Query("picturesOssKeys") String picturesOssKeys, @Query("earlyRecordId") String earlyRecordId);


    /**
     * 一键更新预警状态
     *
     * @param userId
     * @param status
     * @param id
     * @return
     */
    @POST("/earlywarn/early/record/batchUpdateStatus")
    Flowable<BaseBean> getBatchUpdateStatus(@Query("userId") String userId, @Query("status") String status, @Query("id") String id);

    /**
     * 发起维保
     *
     * @return
     */
    @POST("/earlywarn/order/add")
    Flowable<BaseBean> addWeiBao(@Query("userId") String userId, @Query("instCode") String instCode, @Query("type") String type,
                                 @Query("equipmentType") String equipmentType, @Query("faultType") String faultType, @Query("projectId") String projectId,
                                 @Query("picturesOssKeys") String picturesOssKeys, @Query("memo") String memo);

    /**
     * 一键发起维保
     *
     * @param userId
     * @param instCode
     * @param type
     * @param projectId
     * @param recordId
     * @return
     */
    @POST("/earlywarn/order/quickAdd")
    Flowable<BaseBean> getQuickAdd(@Query("userId") String userId, @Query("instCode") String instCode, @Query("type") String type, @Query("projectId") String projectId, @Query("recordId") String recordId);

    /**
     * 极光推送别名绑定接口
     *
     * @return
     */
    @POST("/earlywarn/jpush/bindAlias")
    Flowable<BaseBean> setJPush(@Query("userId") String userId, @Query("registrationId") String registrationId);

    /**
     * 警报系统
     *
     * @return
     */
    @POST("early/record/statisticsByWeek")
    Flowable<StatisticsModel> getErrorData(@Query("userId") String userId, @Query("projectId") String projecId, @Query("instCode") String instCode, @Query("flag") String flag);

    /**
     * 故障率
     *
     * @return
     */
    @POST("early/record/statisticsFaultRateByWeek")
    Flowable<StatisticsModel> getFaultData(@Query("userId") String userId, @Query("projectId") String projecId, @Query("instCode") String instCode, @Query("flag") String flag);


    /**
     * 查岗记录
     *
     * @param page
     * @param size
     * @param projectId
     * @return
     */
    @POST("check/list")
    Flowable<CheckListbean> getChecklist(@Query("page") String page, @Query("size") String size, @Query("projectId") String projectId);

    /**
     * 一键查岗
     *
     * @param userId
     * @param projectId
     * @return
     */
    @POST("check/add2")
    Flowable<BaseBean> getCheckadd2(@Query("userId") String userId, @Query("projectId") String projectId);


    /**
     * 接班二维码获取
     *
     * @param userId
     * @return
     */
    @POST("/earlywarn/handover/qrcode")
    Flowable<BaseBean> getQrcode(@Query("userId") String userId);

    /**
     * 接班结果查询
     *
     * @param userId
     * @return
     */
    @POST("/earlywarn/handover/result")
    Flowable<BaseBean> getQrcodeResult(@Query("userId") String userId);


    /**
     * 扫码交班
     *
     * @param userId
     * @param msg
     * @return
     */
    @POST("/earlywarn/handover/proccess")
    Flowable<BaseBean> getQrcodeProccess(@Query("userId") String userId, @Query("msg") String msg);


    /**
     * 扫码交班
     *
     * @param userId
     * @param
     * @return
     */
    @POST("/earlywarn/handover/proccess")
    Flowable<BaseBean> getQrcodeProccess2(@Query("userId") String userId);

    /**
     * 提建议
     *
     * @param userId
     * @param text
     * @return
     */
    @POST("advice/add")
    Flowable<BaseBean> getAdviceadd(@Query("userId") String userId, @Query("text") String text);


    /**
     * @param userId
     * @param earlyRecordId
     * @return
     */
    @POST("/earlywarn/early/event/report/list")
    Flowable<EventReportListbean> getEventReportList(@Query("userId") String userId, @Query("earlyRecordId") String earlyRecordId);

    /**
     * 获取摄像头ID
     *
     * @param userId
     * @return
     */
    @POST("/earlywarn/camera/list")
    Flowable<CameraListBean> getCameraList(@Query("userId") String userI, @Query("projectId") String projectId, @Query("distributionStatus") String distributionStatus);

    @POST("/earlywarn/camera/list")
    Flowable<CameraListBean> getCameraList2(@Query("userId") String userI);


    /**
     * @param userId
     * @param id
     * @return
     */
    @POST("camera/preview_urls")
    Flowable<BaseBean> getCameraurl(@Query("userId") String userId, @Query("id") String id);


    /**
     * 首页统计接口
     *
     * @param instCode
     * @param projectId
     * @return
     */

    @POST("early/record/homePageStatistics")
    Flowable<HomePageStatisticsBean> getHomePageStatistics(@Query("instCode") String instCode, @Query("projectId") String projectId);

    /**
     * 日常维保记录
     *
     * @param page
     * @param size
     * @param userId
     * @param coverProjectId
     * @return
     */
    @POST("maintenance/record/listApp")
    Flowable<RecordListAppBean> getRecordlistApp(@Query("page") String page, @Query("size") String size, @Query("userId") String userId, @Query("coverProjectId") String coverProjectId);


    @POST("maintenance/record/detail")
    Flowable<MaintenanceRecordDetailBean> getMaintenanceRecordDetail(@Query("id") String id);


    @POST("permission/list")
    Flowable<PermissionListBean> getPermissionList(@Query("groupType") String groupType);


    //1.1.3需求


    /**
     * 电路监控列表
     *
     * @param page
     * @param size
     * @return
     */
    @POST("collector/app/powerListApp")
    Flowable<SmartElectorBean> getPowerListApp(@Query("page") String page, @Query("size") String size, @Query("projectId") String projectId);

    /**
     * 电路监控详情
     *
     * @param id
     * @return
     */
    @POST("collector/app/powerDetailApp")
    Flowable<SmartElectorDetailBean> getPowerDetailApp(@Query("id") String id);

    /**
     * 巡查记录列表
     *
     * @param projectId
     * @param projectName
     * @param startTime
     * @param endTime
     * @param userName
     * @param page
     * @param size
     * @return
     */
    @POST("patrol/record/app/list")
    Flowable<SmartPatrolBean> getPatrolRecordList(@Query("projectId") String projectId, @Query("projectName") String projectName, @Query("startTime") String startTime, @Query("endTime") String endTime, @Query("userName") String userName, @Query("page") String page, @Query("size") String size);


    /**
     * 巡查人列表
     *
     * @return
     */

    @POST("patrol/record/app/patrolUserList")
    Flowable<PatrolUserListBean> getPatrolUserList();

    /**
     * 判断巡查是否结束
     *
     * @return
     */
    @POST("patrol/record/app/getPatrolEndStatus")
    Flowable<PatrolEndStatusBean> gtPatrolEndStatus();

    /**
     * 结束巡查
     *
     * @param id
     * @param trajectoryOssKeys
     * @return
     */
    @POST("patrol/record/app/endPatrol")
    Flowable<BaseBean> getEndPatrol(@Query("id") String id, @Query("trajectoryOssKeys") String trajectoryOssKeys);


    /**
     * 巡查点位列表
     *
     * @param name
     * @param projectId
     * @param page
     * @param size
     * @return
     */
    @POST("patrol/record/app/patrolPointList")
    Flowable<PatrolPointListBean> getPatrolPointList(@Query("name") String name, @Query("projectId") String projectId, @Query("page") String page, @Query("size") String size);

    /**
     * 完成巡查点位
     *
     * @param patrolRecordId
     * @param patrolPointCode
     * @param latitude
     * @param longitude
     * @param remark
     * @param picturesOssKeys
     * @param completeType
     * @return
     */
    @POST("patrol/record/app/add")
    Flowable<BaseBean> getPatrolRecordAppAdd(@Query("patrolRecordId") String patrolRecordId, @Query("patrolPointCode") String patrolPointCode, @Query("latitude") String latitude, @Query("longitude") String longitude, @Query("remark") String remark, @Query("picturesOssKeys") String picturesOssKeys, @Query("completeType") String completeType);


    /**
     * 开始巡查
     *
     * @return
     */
    @POST("patrol/record/app/startPatrol")
    Flowable<BaseBean<Integer>> getPatrolAppStartPatrol();

    /**
     * 巡查记录详情
     *
     * @return
     */
    @POST("patrol/record/app/detail")
    Flowable<PatrolMapInfoModel> getPatrolMapInfo(@Query("id") String id);

    /**
     * 设备复位
     *
     * @return
     */
    @POST("collector/app/resetDevice")
    Flowable<BaseBean> getResetDevice(@Query("id") String id);

    /**
     * 判断是否重复巡查
     *
     * @param patrolPointCode
     * @return
     */
    @POST("patrol/record/app/isRepeatPatrol")
    Flowable<BaseBean> getIsRepeatPatrol(@Query("patrolPointCode") String patrolPointCode);


    /**
     * 查询智慧用水采集器列表
     *
     * @param projectId
     * @param type      水设备类型 10002:消火栓压力传感器，10003:液位传感器,10004：水压传感器
     * @return
     */
    @POST("collector/app/waterListApp")
    Flowable<WaterListModel> getWaterList(@Query("projectId") String projectId, @Query("type") String type);


    /**
     * 查询智慧用水采集器详情
     *
     * @param id
     * @return
     */
    @POST("collector/app/waterDetailApp")
    Flowable<WaterModel> getWater(@Query("id") String id);
}
