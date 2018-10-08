package com.wb.weibao.common;




import com.lm.lib_common.model.BaseBean;
import com.wb.weibao.model.LoginModel;

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
     * @param loginAccount
     * @param password
     * @return
     */
    @POST("login/login2")
    Flowable<LoginModel>  getUserLogin(@Query("loginAccount") String loginAccount, @Query("password") String password);

    @POST("project/list")
    Flowable<BaseBean>  getProject_list(@Query("instCode") String instCode, @Query("userId") String userId);


}
