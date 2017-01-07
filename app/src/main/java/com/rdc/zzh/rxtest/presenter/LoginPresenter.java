package com.rdc.zzh.rxtest.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.rdc.zzh.rxtest.model.minterface.LoginModel;
import com.rdc.zzh.rxtest.util.RetrofitUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ZengZeHong on 2017/1/7.
 */

public class LoginPresenter {
    private static final String TAG = "LoginPresenter";
    private RetrofitUtil mRetrofitUtil;
    public LoginPresenter(){
        mRetrofitUtil = RetrofitUtil.getInstance();
    }

    /**
     * 读取验证码图片
     * @param imageView
     */
    public void getImageCode(final ImageView imageView){
        LoginModel loginModel = mRetrofitUtil.createClass(LoginModel.class);
        loginModel.getImageCode().map(new Func1<ResponseBody, Bitmap>() {
            @Override
            public Bitmap call(ResponseBody response) {
                return BitmapFactory.decodeStream(response.byteStream());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                Log.e(TAG, "call: success" );
                imageView.setImageBitmap(bitmap);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "call: fail "   + throwable.toString());
            }
        });
    }

    public void login(final String userName , String passWord , String imageCode){
        final LoginModel loginModel = mRetrofitUtil.createClass(LoginModel.class);
        Map<String , String> params = new HashMap<>();
        try {
            params.put("__VIEWSTATE" , URLEncoder.encode("dDwyODE2NTM0OTg7Oz7QqY3yg91iEh+CrEbxxVUHRHuTxg==" ,"gbk"));
            params.put("txtUserName" ,userName);
            params.put("TextBox2" ,passWord);
            params.put("txtSecretCode" ,imageCode);
            params.put("RadioButtonList1" ,URLEncoder.encode("学生", "GBK"));
            params.put("Button1" ,"");
            params.put("lbLanguage" ,"");
            params.put("hidPdrs" ,"");
            params.put("hidsc" ,"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        loginModel.login(params).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                Log.e(TAG, "call: one " + s );
                return loginModel.getUserInfo(userName);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "call: two " + s );
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "call: fail ");
            }
        });
    }
}
