package com.igw.igw.fragment

import android.view.LayoutInflater
import com.igw.igw.mvp.model.IBaseModel
import com.igw.igw.mvp.presenter.BasePresenter
import com.igw.igw.mvp.view.IBaseView
import com.shengshijingu.yashiji.common.base.BaseDataFragment

/**
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 * @Describe
 */
 abstract  class BaseMvpDataFragment<P : BasePresenter<*,*>> : BaseDataFragment() {


    public  var mPresenter: P? = null


    override fun initBaseView(inflater: LayoutInflater) {
        super.initBaseView(inflater)
        initPresenter()
    }

    protected abstract fun initPresenter()
}