package com.igw.igw.modoule.splash.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.bumptech.glide.Glide
import com.igw.igw.MainActivity
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.SplashBean
import com.igw.igw.modoule.login.loginstate.LoginManager
import com.igw.igw.modoule.login.view.LoginActivity
import com.igw.igw.modoule.splash.SplashContract
import com.igw.igw.modoule.splash.model.SplashModel
import com.igw.igw.modoule.splash.presenter.SplashPresenter
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.LogUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import rx.Observable
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *
 */
class SplashActivity : BaseActivity<SplashPresenter>(),SplashContract.View {


    companion object {

        val TAG = "SplashActivity"
    }

    var count = 5

     var locale : Boolean = true

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//
////        initCountDown()
//
//
//
//    }


//    override fun onResume() {
//        super.onResume()
//        initCountDown()
//
//    }


    private var  countimeSubscription: Subscription? = null
    /**
     * 开启倒计时
     */
    private fun initCountDown() {

        var count = 5 // 秒数

        countimeSubscription  = Observable.interval(0, 1, TimeUnit.SECONDS)
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

                        startMainOrLogin()

                    }
                })


    }

    private fun startMainOrLogin() {
        val login = LoginManager.instance.isLogin()
        LogUtils.d(TAG, "获取登录状态  ===  $login")

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

    override fun initView() {
        locale = LocaleUtils.isLocaleEn(this)
        mPresenter.splashNet(1)


        setUpLisetener()

    }

    private fun setUpLisetener() {

        bg_splash.setOnClickListener{
//            /点击时跳转
        }


        tv_count.setOnClickListener {

            countimeSubscription?.let {

                if (!it.isUnsubscribed){
                    it.unsubscribe()

                    startMainOrLogin()

                }
            }
        }
    }

    override fun initPresenter() {
         mPresenter = SplashPresenter(SplashModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int  = R.layout.activity_splash

    override fun setTitle(): String  =  ""

    override fun setRightButton(): String  =  ""

    override fun setStatusBarColor(): Boolean {
       return false

    }

    override fun onSuccess(data: SplashBean.DataBean) {
        initCountDown()
        GlideUtils.loadImage(this,data.advertiseInfo.image,bg_splash)
    }

    override fun onFail(code: Int, msg: String) {
        initCountDown()

    }

    override fun fail(o: Any?) {
        TODO("Not yet implemented")
    }

    override fun success(o: Any?) {
        TODO("Not yet implemented")
    }
}


