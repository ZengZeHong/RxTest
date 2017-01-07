package com.rdc.zzh.rxtest.util;

/**
 * Created by ZengZeHong on 2016/9/26.
 */

public class ApiUtil {
    //TODO 要存进数据库读取，不然内存回收会crash，所有静态变量会被重置
    //教务处主页
    public static final String URL_HOST_ONE = "http://jwgl.gdut.edu.cn";
    public static final String HOST = "jwgldx.gdut.edu.cn";
    public static final String URL_HOST_TWO = "http://jwgldx.gdut.edu.cn/";
    public static final String VIEWSTATE = "dDwyODE2NTM0OTg7Oz7QqY3yg91iEh+CrEbxxVUHRHuTxg==";

    //获取验证码
    public static final String DIRECTORY_LOGIN_IMAGE_CODE = "CheckCode.aspx?";
    //等一次录入登陆信息 post='/zdy.htm?aspxerrorpath=/default2.aspx
    public static final String DIRECTORY_LOGIN_FIRST = "default2.aspx";
    //第二次登陆 get 后面加入对应的路径，由上一次登陆得来的结果录入 + 3114005890"/xs_main.aspx?xh=3114005890";
    public static final String DIRECTORY_LOGIN_SECOND =  "xs_main.aspx";

}
