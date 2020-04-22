package com.igw.igw.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.data.LocalUriFetcher
import com.igw.igw.MainActivity
import com.igw.igw.R
import com.igw.igw.modoule.login.loginstate.LoginManager
import com.igw.igw.modoule.login.view.LoginActivity
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import io.reactivex.Scheduler
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 */
class SplashActivity : AppCompatActivity() {


    companion object {

        val TAG = "SplashActivity"
    }

    var count = 5

     var locale : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        initCountDown()

        locale = LocaleUtils.isLocaleEn(this)

    }


    override fun onResume() {
        super.onResume()
        initCountDown()

    }

    /**
     * 开启倒计时
     */
    private fun initCountDown() {

        var count = 5 // 秒数

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map { t -> count - t }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onError(e: Throwable?) {
                    }

                    override fun onNext(t: Long?) {


                        LogUtils.d(TAG, "倒计时 $t")
                        tv_count?.let {

                            if (locale) {

                                it.text = "skip in ${t?.toInt()} seconds\n"
                            } else {
                                it.text = "跳过${t?.toInt()}s"
                            }

//
                        }
                    }

                    override fun onCompleted() {

                        val login = LoginManager.instance.isLogin()

                        if (login) {

                            val intent = Intent(this@SplashActivity, MainActivity::class.java)
                            this@SplashActivity.startActivity(intent)
                            finish()
                        } else {

                            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                            this@SplashActivity.startActivity(intent)
                            finish()
                        }

                    }
                })


    }
}
