package com.shengshijingu.yashiji.common.net;

import java.util.Map;

import com.shengshijingu.yashiji.common.bean.WeChatUserInfo;
import com.shengshijingu.yashiji.common.bean.WechatInfo;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * 创建时间  2019/5/16 2:07 PM .
 *
 * 作者  雷雷
 */

public interface WeChatApiService {

    @GET("/sns/oauth2/access_token")
    Observable<WechatInfo> getWechatOpenId(@QueryMap Map<String, String> params);

}
