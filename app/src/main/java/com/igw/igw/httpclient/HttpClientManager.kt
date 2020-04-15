package com.igw.igw.httpclient

import android.os.Debug
import com.igw.igw.app.IGWApplication
import com.igw.igw.utils.AppUtils
import com.igw.igw.utils.AppUtils.appContext
import com.igw.igw.utils.LogUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.Exception

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class HttpClientManager private constructor() {

    companion object {
        val TAG = HttpClientManager::class.java.simpleName

        private var CONNECT_TIMEOUT = 10L
        private var READ_TIMEOUT = 10L
        private var WRITE_TIMEOUT = 10L


        val INSANCE: HttpClientManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {

            HttpClientManager()
        }

        private var mBaseUrl: String? = null

        private var mOkHttpClient: OkHttpClient? = null


        fun initRetrofit(): Retrofit {


            if (mBaseUrl == null) {

                LogUtils.e(TAG, "the baseUrl is not set !!")

                throw Exception("the baseUrl is not set !!")
            } else {

                return Retrofit.Builder()
                    .baseUrl(mBaseUrl!!)
                    .client(initHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

        }


        fun initBaseUrl(baseUrl: String) {
            mBaseUrl = baseUrl

        }

        /**
         * 初始化 client
         */
        fun initHttpClient(): OkHttpClient {

            if (mOkHttpClient == null) {

                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                synchronized(HttpClientManager::class.java) {

//                    val cache = Cache(File(IGWApplication.appContext.cacheDir.toString(), "HttpCache"), 1024 * 1024 * 100)

                    val cache = Cache(File(appContext.cacheDir.toString(), "HttpCache"), 1024 * 1024 * 100)

                    if (mOkHttpClient == null)
                        mOkHttpClient = OkHttpClient.Builder()
                            .cache(cache)
                            .retryOnConnectionFailure(true)
                            .addInterceptor(httpLoggingInterceptor)
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .build()
                }


            }

            return mOkHttpClient!!
        }

    }

    /**
     * 返回一个service
     */
    fun <S> getHttpService(clazz: Class<S>): S {

        return initRetrofit().create(clazz)
    }

}