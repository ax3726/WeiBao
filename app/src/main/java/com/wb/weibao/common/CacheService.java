package com.wb.weibao.common;



/**
 * Created by Administrator on 2017/11/22 0022.
 * 缓存统一处理服务类
 */

public class CacheService {

    private static CacheService mCacheService = null;

    private CacheService() {

    }

    public static CacheService getIntance() {
        if (mCacheService == null) {
            mCacheService = new CacheService();
        }
        return mCacheService;
    }

/*
    *//**
     * 读取用户信息
     *
     * @return
     *//*

    public UserModel getUser() {
        return (UserModel) CacheUtils.getInstance().loadCache(Constant.USER_INFO);
    }


    *//**
     * 是否登录
     *
     * @return
     *//*

    public boolean isLogin() {
        return CacheUtils.getInstance().loadCache(Constant.USER_INFO) != null;
    }


    *//**
     * 缓存用户信息
     *
     * @param userInfo
     *//*

    public void setUser(UserModel userInfo) {
        CacheUtils.getInstance().saveCache(Constant.USER_INFO, userInfo);
    }


    *//**
     * 清除用户信息
     *
     * @param
     *//*

    public void clearUser() {
        CacheUtils.getInstance().removeCache(Constant.USER_INFO);
    }*/

}
