package com.igw.igw.fragment

import com.igw.igw.mvp.presenter.BasePresenter
import com.shengshijingu.yashiji.common.base.BaseOldFragment

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
open abstract  class BaseMvpFragment<P : BasePresenter<*,*>> : BaseOldFragment(){



    private var mPresenter: P? = null

    override fun initView() {

        initPresenter()
    }

    abstract fun initPresenter()

}