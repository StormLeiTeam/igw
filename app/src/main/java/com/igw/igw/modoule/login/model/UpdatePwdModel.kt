package com.igw.igw.modoule.login.model

import com.igw.igw.bean.login.UpdatePwdBean
import com.igw.igw.modoule.login.UpdatePwdContract
import com.igw.igw.network.NetObserver
import com.shengshijingu.yashiji.common.util.ControllerUtils

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class UpdatePwdModel: UpdatePwdContract.Model {

    companion object{
        val TAG = "UpdatePwdModel"
    }


    /**
     *
     */
    override fun updatePwd(oldPassword: String, newPassword: String, observer: NetObserver<UpdatePwdBean>) {

        ControllerUtils.getLoginControllerInstance().updatePassword(oldPassword,newPassword,observer)

    }
}