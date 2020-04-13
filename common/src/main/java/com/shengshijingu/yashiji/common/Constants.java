package com.shengshijingu.yashiji.common;

import android.content.Context;

/**
 * Created by leilei on 2019/3/8.
 */

public class Constants {

    public static Context context;

    public static String userId;

    public static String chanleName;

    public static String authorization = "";

    public final static int ENVIRONMENT_DEBUG = 1;//测试

    public final static int ENVIRONMENT_RELEASE = 2;//正式

    public final static String LICENCEURL = "http://license.vod2.myqcloud.com/license/v1/ea0f95085de4fd6cc3427a6239058347/TXLiveSDK.licence";

    public final static String LICENCEKEY = "47ea02eef76447bc8beae06ff683e9d4";

    public static String BASE_URL = null; //测试域名

    public static String HTML_BASE_URL = null; //H5正式域名

    public static String WX_APP_ID = null;  //微信

    public static String WX_APP_SECRET = null; //微信秘钥

    public static String QQ_APP_ID = null;  //QQ

    public static String WB_APP_KEY = null; //微博

    public static String IM_APP_ID = null;// 腾讯云 IM

    public static String XM_PUSH_APPID = null; //小米

    public static String XM_PUSH_APPKEY = null;

    public static String IS_GROUP = null;

    public static String INTENT_DATA = "identify";

    public static String USERPROTOCOL = "https://api.yashijitv.com/protocol1/about/html/agreement.html";

    public static String ABOUTUS = "http://share.yashijitv.com/about/about.html";

    public static String QUESTIONNAIRE = "https://wj.qq.com/s2/4285202/7168/";

    public static final String DOMAIN_WECHAT = "https://api.weixin.qq.com/";

    public static final String LIVEREPORT = "https://api.yashijitv.com/protocol1//about/liveReport/html/liveReport.html";

    //填写自己创建时自己填的回调页url
    public static String REDIRECT_URL = "http://open.weibo.com/apps/3138615728/info/advanced";  //微博 REDIRECT_URL

    /*
     *   Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。
     *   目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
     *   有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
     *   关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
      */
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

}
