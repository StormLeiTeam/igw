package com.shengshijingu.yashiji.common.net.Interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class CommonHeaderInterceptor : Interceptor {


    companion object{
        val TAG = "CommonHeaderInterceptor"

        public var token : String = ""


    }


    override fun intercept(chain: Interceptor.Chain): Response {



        val oldRequest = chain.request()

//        val diichHeader = EncryptUtils.getCommonDiich(stamp)

        val request = oldRequest.newBuilder()

//        var auth = LoginState.TOURIST
//        if (UserManager.mInstance.mLoginState) {
//            auth = UserManager.mInstance.mAccess_token
//        }

        Log.d(TAG,"token--> " + token)
        request.addHeader("X-Auth-Token", token)
//        request.addHeader("stamp",stamp)
//        request.addHeader("diich",diichHeader)


        return chain.proceed(request.build())

    }
}