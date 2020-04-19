package com.igw.igw.bean.login

import com.igw.igw.utils.FileUtils
import java.io.File

/**
 *
 * @author storm_z
 * @date @{DATE}
 * @email zq329051@outlook.com
 *
 * @Describe
 */
class RegisterBean {


    var countryId: Int? = null

    var cityId: Int? = null

    var lastName: String? = null

    var firstName: String? = null
    var sex: Int? = null

    var birthday: String? = null
    var nickname: String? = null
    var agencyName: String? = null
    var description: String? = null
    var email: String? = null
    var mobilePhone: String? = null
    var password: String? = null
    var inviteCode: String? = null
    var headImage: String? = null


    fun setHeadImage(file: File) {


        FileUtils.File2bytes(file)?.let {
            headImage = FileUtils.bytes2String(it)
        }
    }





    override fun toString(): String {
        return "RegisterBean(countryId=$countryId, cityId=$cityId, lastName=$lastName, firstName=$firstName, sex=$sex, birthday=$birthday, nickname=$nickname, agencyName=$agencyName, description=$description, email=$email, mobilePhone=$mobilePhone, password=$password, inviteCode=$inviteCode, headImage=$headImage)"
    }


}