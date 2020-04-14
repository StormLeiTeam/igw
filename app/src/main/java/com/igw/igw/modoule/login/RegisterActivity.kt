package com.igw.igw.modoule.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.mvp.view.IBaseView

/**
 * 注册模块
 */

class RegisterActivity : BaseActivity<BasePresenter<IBaseView>>() {
    override fun initView() {


    }

    override fun initPresenter() {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun setTitle(): String {
        TODO("Not yet implemented")
    }

    override fun setRightButton(): String {
        TODO("Not yet implemented")
    }

    override fun setStatusBarColor(): Boolean {
        TODO("Not yet implemented")
    }


}
