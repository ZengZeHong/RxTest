package com.rdc.zzh.rxtest.model.minterface;

import com.rdc.zzh.rxtest.util.ApiUtil;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ZengZeHong on 2017/1/7.
 */

public interface LoginModel {
    //获取验证码
    @GET(ApiUtil.DIRECTORY_LOGIN_IMAGE_CODE)
    Observable<ResponseBody> getImageCode();

    //第一次登录
    @FormUrlEncoded
    @POST(ApiUtil.DIRECTORY_LOGIN_FIRST)
    Observable<String> login(@FieldMap Map<String , String> options);

    //第二次登录
    @GET(ApiUtil.DIRECTORY_LOGIN_SECOND)
    Observable<String> getUserInfo(@Query("xh") String studentNumber);
}
