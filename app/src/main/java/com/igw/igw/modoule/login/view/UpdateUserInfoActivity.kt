package com.igw.igw.modoule.login.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.*
import com.igw.igw.modoule.login.UpdateInfoContract
import com.igw.igw.modoule.login.model.UpdateUserInfoModel
import com.igw.igw.modoule.login.presenter.UpdateUserInfoPresenter
import com.igw.igw.utils.*
import com.igw.igw.widget.ChoicePopWindow
import com.igw.igw.widget.storm.StatusBarView
import com.igw.igw.widget.storm.popwindowselect.popselectview.WheelViewPopupwindow
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.CropOptions
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.util.ToastUtil
import kotlinx.android.synthetic.main.activity_update_user_info.*
import kotlinx.android.synthetic.main.common_status_bar.*
import java.io.File
import java.util.*

/**
 * 修改个人设置
 */
class UpdateUserInfoActivity : BaseActivity<UpdateUserInfoPresenter>(), UpdateInfoContract.View,
        TakePhoto.TakeResultListener, InvokeListener {


    companion object {

        val TAG = "UpdateUserInfoActivity"


        val GENDER_JSON = "[{\"chName\":\"男\",\"enName\":\"女\",\"id\":1,\"isEnglish\":false},{\"chName\":\"女\",\"enName\":\"women\",\"id\":2,\"isEnglish\":false}]"

//
//        fun startSelfforResult(activity: View.OnClickListener, data: String) {
//            var intent = Intent(activity, UpdateUserInfoActivity::class.java)
//            intent.putExtra("user_info", data)
//            startActivityForResult(intent,1001)
//        }
    }


    // 国籍选择器
    private var nationalityPopWindow: WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>? = null

    // 城市
    private var cityPopwindow: WheelViewPopupwindow<CityListBean.DataBean.CitysBean>? = null

    // 性别
    private var genderPopWindow: WheelViewPopupwindow<GenderBean>? = null


    //国籍
    private var mCountrys: List<NationalityBean.DataBean.CountrysBean>? = null

    // 城市
    private var mCitys: List<CityListBean.DataBean.CitysBean>? = null


    private var mUserInfo: UserInfoBean.DataBean? = null


    private var isCityClick: Boolean = false


    private var pvTime: TimePickerView? = null
    private var pickerBuilder: TimePickerBuilder? = null
    private var mChiocePopwindow: ChoicePopWindow? = null

    // 性别
    private var genders: List<GenderBean>? = null


    private var takePhoto: TakePhoto? = null
    private var invokeParam: InvokeParam? = null
    private var cropOptions: CropOptions? = null
    private var compressConfig: CompressConfig? = null
    private var imageUri: Uri? = null

    // 注册提交参数
    private var mNationality: NationalityBean.DataBean.CountrysBean? = null  // 国籍 必填
    private var mCity: CityListBean.DataBean.CitysBean? = null // 城市 必填
    private var mExplain: String = "" //性 必填
    private var mLastNameExplain: String = "" // 名 必填
    private var mGender: GenderBean? = null // 性别 必填
    private var mBirthday: String = "" // 出生日期 必填
    private var mNickName: String = "" //  昵称 选填
    private var mAgencyName: String = "" // 机构/学校 选填
    private var mDescription: String = ""  // 自我介绍
    private var mEmail: String = "" // 自我介绍 // 选填
    private var mMobilePhone: String = "" // 手机号码 必填
    private var mPassword: String = ""  // 密码 必填
    private var mInviteCode: String = "" // 邀请码
    private var mHeadImage: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        getTakePhoto().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        getTakePhoto().onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)

    }


    override fun initView() {

        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle(resources.getString(R.string.title_update_userinfo))
        status_bar_main.setTitleTextSize(16f)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextSize(15F)



        setUpPopWindow()
        initData()
        setUpImagePicker()
        // 获取国籍信息
        getCountryForNet()
        getLocalUserinfo()

        setUpListener()
    }

    private fun setUpImagePicker() {


        takePhoto = getTakePhoto()
        cropOptions = CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create()
        compressConfig = CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create()
        takePhoto?.onEnableCompress(compressConfig, true)


    }

    private fun getTakePhoto(): TakePhoto {

        if (takePhoto == null) {
            takePhoto = (TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this))) as TakePhoto
        }

        return takePhoto!!

    }

    private fun initData() {


        mChiocePopwindow = ChoicePopWindow(this)

        genders = GsonUtils.getInstance().fromJsonString2list<GenderBean>(GENDER_JSON, GenderBean::class.java)

        genderPopWindow?.let {

            it.setData(genders)


            when (LocaleUtils.getCurrentlocale(this)) {

                LocaleUtils.LOCALE_ENGLISH -> {

//                    mCountrys.in

                    for (item: GenderBean in genders!!) {


                        item.isEnglish = true
                    }
                }

                LocaleUtils.LOCALE_CHINESE -> {

                    for (item: GenderBean in genders!!) {


                        item.isEnglish = false
                    }
                }


            }

            it.setSelectItemPosition(0)
        }


    }

    private fun setUpListener() {


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {



                LocaleUtils.changeLocale(this@UpdateUserInfoActivity)
            }


        })

        // 注册信息提交
        btn_submit.setOnClickListener {


//            updateUserInfoForNet()





            if (mHeadImage != null && mHeadImage!!.exists()) {
                // 如果有头像文件

                mPresenter.uploadImaga(mHeadImage!!)

            }else{

                updateUserInfoForNet()
            }

        }


        // 从相册选择图片
        mChiocePopwindow?.setOnButtonChoiceOneListener(object : ChoicePopWindow.OnButtonChoiceOneListener {
            override fun onClick() {

                val imageRri = getImageCropUri()


                takePhoto?.onPickFromGalleryWithCrop(imageRri, cropOptions)
            }

        })

        mChiocePopwindow?.setOnButtonChoiceTwoListener(object : ChoicePopWindow.OnButtonChoiceTwoListener {
            override fun onClick() {

                val url = getImageCropUri()
                takePhoto?.onPickFromCaptureWithCrop(url, cropOptions)

            }

        })


        iv_head_img.setOnClickListener {

            // 获取头像图片
            selectHeadImg()


        }



        tv_select_ymd.setOnClickListener {

            // 时间
//            showDateOfbrith()
            showBirthSelect()
        }



        genderPopWindow?.onCommitlistener { data, position ->

            mGender = data

            if (LocaleUtils.isLocaleEn(this)) {
                tv_select_gender.text = mGender!!.getEnName()
            } else {
                tv_select_gender.text = mGender!!.getChName()
            }

        }

        /**
         * 城市确认
         */
        cityPopwindow?.onCommitlistener { data, position ->


            this.mCity = data

            if (LocaleUtils.isLocaleEn(this)) {
                tv_select_city.text = mCity!!.cityEn

            } else {
                tv_select_city.text = mCity!!.cityCn

            }


        }


        /**
         * 国籍选中监听
         */
        nationalityPopWindow?.onCommitlistener { data, position ->


            this.mNationality = data

            if (LocaleUtils.isLocaleEn(this)) {
                tv_select_nationality.text = mNationality!!.countryEnName

            } else {
                tv_select_nationality.text = mNationality!!.countryCnName

            }

            mCitys = null
            mPresenter.getCityData(mNationality!!.id, false)
            isCityClick = false


        }




        tv_select_gender.setOnClickListener {


            if (genderPopWindow != null && !genderPopWindow!!.isShowing) {
                genderPopWindow!!.animationStyle = R.style.pop_anim
                genderPopWindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, UIUtils.getNavigationHeight(this))
            }
        }



        tv_select_city.setOnClickListener {

            SoftInputUtils.hideSoftInput(this, tv_select_city)

            if (mCitys != null) {

                if (cityPopwindow != null && !cityPopwindow!!.isShowing) {
                    cityPopwindow!!.animationStyle = R.style.pop_anim
                    cityPopwindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, UIUtils.getNavigationHeight(this))
                }

            } else {

//
                if (mNationality == null) {

                    ToastUtil.showCenterToast(this, "请先选择国籍")
                    return@setOnClickListener

                } else {

                    mPresenter.getCityData(mNationality!!.id, false)
                    isCityClick = true
                }

            }


        }


        tv_select_nationality.setOnClickListener {
            SoftInputUtils.hideSoftInput(this, tv_select_nationality)
            if (nationalityPopWindow != null && !nationalityPopWindow!!.isShowing) {
                nationalityPopWindow!!.animationStyle = R.style.pop_anim
                nationalityPopWindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
            }
        }


    }



    private fun updateUserInfoForNet() {

        if (mNationality == null) {
            ToastUtil.showCenterToast(this, R.string.warning_nationality)
            return
        }


        if (mCity == null) {
            ToastUtil.showCenterToast(this, R.string.warning_city)
            return
        }



        mExplain = et_explain.text.toString().trim()
        if (mExplain.isEmpty()) {
            ToastUtil.showCenterToast(this, R.string.warning_explain)
            return

        }


        mLastNameExplain = et_lastname_explain.text.toString().trim()
        if (mLastNameExplain.isEmpty()) {

            ToastUtil.showCenterToast(this, R.string.warning_lastname_explain)
            return
        }



        if (mGender == null) {

            return
        }


        mBirthday = tv_select_ymd.text.toString().trim()
        LogUtils.d(TAG, "获取的生日  -->  $mBirthday")

        if (mBirthday.isEmpty()) {

            return
        }


        mNickName = et_nickname.text.toString().trim()
        mAgencyName = et_agencyname.text.toString().trim()
        mDescription = et_description.text.toString().trim()
        mEmail = et_email.text.toString().trim()
        mMobilePhone = et_phonenumber.text.toString().trim()

        if (mMobilePhone.isEmpty() && mMobilePhone.length != 11) {
            // 手机号码不能为空且 11位

            return
        }


        if (et_password.text.toString().trim().isEmpty()) {

            // 为空
            return
        }

        if (et_password.text.toString().trim().isEmpty() && !(et_password.text.toString().trim()).equals(et_password_again.text.toString().trim())) {

            // 密码不一直
            return
        }

        mPassword = et_password.text.toString().trim()


        mInviteCode = et_inviteCode.text.toString().trim()


        var registerBean = RegisterBean()


        registerBean.countryId = mNationality!!.id
        registerBean.cityId = mCity!!.id
        registerBean.lastName = mExplain
        registerBean.firstName = mLastNameExplain
        registerBean.sex = mGender!!.id
        registerBean.birthday = mBirthday
        registerBean.nickname = mNickName
        registerBean.agencyName = mAgencyName
        registerBean.userDesc = mDescription
        registerBean.email = mEmail
        registerBean.mobilePhone = mMobilePhone
        registerBean.password = mPassword
        registerBean.inviteCode = mInviteCode

        if (!mImagePath.isEmpty()){
            registerBean.headImage =  mImagePath

        }
//        mHeadImage?.let {
//            registerBean.setHeadImage(it)
//
//        }

        mPresenter.updateUserInfo(registerBean)

    }

    private fun getImageCropUri(): Uri? {


        var file = File(Environment.getExternalStorageDirectory(), "/temp/${System.currentTimeMillis()}.jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        return Uri.fromFile(file)

    }


    private fun selectHeadImg() {


        if (mChiocePopwindow == null) {

            mChiocePopwindow = ChoicePopWindow(this)
        }

        mChiocePopwindow?.let {

            if (LocaleUtils.isLocaleEn(this)) {

                it.setCancelText("cancle")
                it.setChoiceOneText("select ")
                it.setChoiceTwoText("take photo")
            } else {

                it.setCancelText("取消")
                it.setChoiceOneText("从相册选取图片")
                it.setChoiceTwoText("拍照")
            }

            if (!it.isShowing) {
                it.showAtLocation(root_main,
                        Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)

            }
        }


    }

    private fun showBirthSelect() {


        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        //startDate.set(2013,1,1);
        //startDate.set(2013,1,1);
        val endDate = Calendar.getInstance()
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1)
        endDate.set(2020, 12, 30)

        if (pickerBuilder == null) {

            pickerBuilder = TimePickerBuilder(this, OnTimeSelectListener { date, v -> //选中事件回调


                // 选中

                var time: String = TimesUtils.data2String(date)
                LogUtils.d(TAG, "获取的生日 --> $time")
                tv_select_ymd.text = time
                mBirthday = time

            })
                    .setType(booleanArrayOf(true, true, true, false, false, false)) // 默认全部显示
//                    .setCancelText("Cancel") //取消按钮文字
//                    .setSubmitText("Sure") //确认按钮文字
                    .setContentTextSize(18)
                    .setTitleSize(20) //标题文字大小
                    .setTitleText("") //标题文字
                    .setOutSideCancelable(false) //点击屏幕，点在控件外部范围时，是否取消显示
                    .isCyclic(true) //是否循环滚动
                    .setTitleColor(Color.BLACK) //标题文字颜色
                    .setSubmitColor(Color.BLACK) //确定按钮文字颜色
                    .setCancelColor(Color.BLACK) //取消按钮文字颜色
                    .isDialog(true)
                    .setTitleBgColor(Color.WHITE)
                    .setBgColor(Color.WHITE) //滚轮背景颜色 Night mode
                    .setDate(selectedDate) // 如果不设置的话，默认是系统时间*/
                    .setRangDate(startDate, endDate) //起始终止年月日设定
                    .setLabel("", "", "", "时", "分", "秒") //默认设置为年月日时分秒
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .isDialog(true) //是否显示为对话框样式


        }

        if (!LocaleUtils.isLocaleEn(this)) {
            //ture

            pvTime = pickerBuilder!!.setCancelText("取消")
                    .setSubmitText("确认").build()
        } else {

            pvTime = pickerBuilder!!.setCancelText("Cancel")
                    .setSubmitText("Sure").build()

        }


        var dialog = pvTime!!.dialog

        if (dialog != null) {

            var params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)

            params.leftMargin = 0
            params.rightMargin = 0
            pvTime!!.dialogContainerLayout.layoutParams = params

            var dialogWindow = dialog.window

            dialogWindow?.let {

                it.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)
                dialogWindow.setGravity(Gravity.BOTTOM)

            }
        }

        pvTime!!.show()


    }

    private fun setUpPopWindow() {


        // 国籍选择
        nationalityPopWindow = WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>(this)

        // 城市
        cityPopwindow = WheelViewPopupwindow<CityListBean.DataBean.CitysBean>(this)

        // 性别
        genderPopWindow = WheelViewPopupwindow<GenderBean>(this)


    }

    private fun getCountryForNet() {

        mPresenter.getNationalityData()
    }

    private fun getLocalUserinfo() {
        var json = intent.getStringExtra("user_info")


        mUserInfo = GsonUtils.getInstance().fromJson(json, UserInfoBean.DataBean::class.java)


        mUserInfo?.let {
            mPresenter.getCityData(it.countryId, true)

        }

        updateUserInfoForLocal()
    }


    // 数据回显
    private fun updateUserInfoForLocal() {

        //

        genders?.let {
            for (item: GenderBean in it) {

                if (item.id == mUserInfo?.sex) {
                    mGender = item


                }

            }

        }

        mGender?.let {
            if (LocaleUtils.isLocaleEn(this)) tv_select_gender.text = it.enName else tv_select_gender.text = it.chName

        }


        mUserInfo?.let {


            et_explain.setText(it.lastName)
            et_lastname_explain.setText(it.firstName)
            tv_select_ymd.text = it.birthday
            et_nickname.setText(it.nickName)
            et_agencyname.setText(it.agencyName)
            et_description.setText(it.userDesc)
            et_email.setText(it.email)
            et_phonenumber.setText(it.mobilePhone)
//            et_password.setText(it.password)
//            et_password_again.setText(it.password)
//            et_inviteCode.setText(it.inviteCode)

            if (it.inviteCode != null) {

                et_inviteCode.setText(it.inviteCode.toString())

            }

            if (it.headImage != null) {
                val headImage = it.headImage.toString()

                LogUtils.d(TAG,"获取的图片地址  --> ${Constants.BASE_URL +  headImage} ")
                GlideUtils.loadImage(this,  Constants.BASE_URL + headImage, iv_head_img)
            }


        }


    }

    override fun initPresenter() {
        mPresenter = UpdateUserInfoPresenter(UpdateUserInfoModel())
        mPresenter.attachView(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_update_user_info
    }

    override fun setTitle(): String {
        return ""
    }

    override fun setRightButton(): String {

        return ""
    }

    override fun setStatusBarColor(): Boolean {
        return false
    }

    override fun onSuccessNationality(countrys: List<NationalityBean.DataBean.CountrysBean>) {

        this.mCountrys = countrys

        nationalityPopWindow?.let {


            it.setData(mCountrys)
            when (LocaleUtils.getCurrentlocale(this)) {

                LocaleUtils.LOCALE_ENGLISH -> {

//                    mCountrys.in

                    for (item: NationalityBean.DataBean.CountrysBean in mCountrys!!) {


                        item.isEnglish = true
                    }
                }

                LocaleUtils.LOCALE_CHINESE -> {

                    for (item: NationalityBean.DataBean.CountrysBean in mCountrys!!) {


                        item.isEnglish = false
                    }
                }
            }




            it.setSelectItemPosition(0)
        }


        val countryId = mUserInfo?.countryId

        if (mCountrys != null && mCountrys!!.size > 0) {

            for (item: NationalityBean.DataBean.CountrysBean in mCountrys!!) {

                if (item.id == countryId) {
                    mNationality = item

                }
            }
        }

        if (LocaleUtils.isLocaleEn(this)) {

            // 英语
            tv_select_nationality.text = mNationality?.countryEnName

        } else {
            // 中文
            tv_select_nationality.text = mNationality?.countryCnName

        }


    }


    override fun onFailNationality() {


    }

    override fun onSuccessCitys(citys: List<CityListBean.DataBean.CitysBean>, isLocal: Boolean) {

        this.mCitys = citys

        if (isLocal) {

            for (item: CityListBean.DataBean.CitysBean in mCitys!!) {

                if (mUserInfo?.cityId == item.id) {
                    //相等
                    mCity = item

                    if (LocaleUtils.isLocaleEn(this)) {
                        tv_select_city.text = mCity?.cityEn
                    } else {
                        tv_select_city.text = mCity?.cityCn

                    }
                }
            }

        } else {

            cityPopwindow?.let {

                it.setData(mCitys)

                when (LocaleUtils.getCurrentlocale(this)) {

                    LocaleUtils.LOCALE_ENGLISH -> {

//                    mCountrys.in

                        for (item: CityListBean.DataBean.CitysBean in mCitys!!) {


                            item.isEnglish = true
                        }
                    }

                    LocaleUtils.LOCALE_CHINESE -> {

                        for (item: CityListBean.DataBean.CitysBean in mCitys!!) {


                            item.isEnglish = false
                        }
                    }


                }

                it.setSelectItemPosition(0)

            }



            if (cityPopwindow != null && !cityPopwindow!!.isShowing && isCityClick) {
                cityPopwindow!!.animationStyle = R.style.pop_anim
                cityPopwindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, UIUtils.getNavigationHeight(this))
            }

        }


    }

    override fun onFailCitys() {


    }


    private var  mImagePath: String =  ""
    //上传图片成功的回调
    override fun loadHeadImageSuccessful(data: HeadImageBean.DataBean) {
        this.mImagePath = data.attachmentUrl

        GlideUtils.loadImage(this,Constants.BASE_URL + mImagePath,iv_head_img)
        updateUserInfoForNet()

    }

    // 上传图片失败
    override fun loadHeadImageFail(code: Int, msg: String) {

        ToastUtil.showCenterToast(this,msg)
    }

    override fun updateUserInfoSuccessful(data: UserInfoBean.DataBean) {

        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun updateUserInfoFail(code: Int, msg: String) {


    }

    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }


    override fun takeSuccess(result: TResult?) {

        LogUtils.d(TAG, "takePhoto --> takeSuccess -> ${result?.image?.originalPath}")

        // 返回文件 路径
        result?.let {
            mHeadImage = File(it.image?.originalPath)

            LogUtils.d(TAG, "获取的文件是否存在 -> ${mHeadImage!!.exists()}"


            )

            if (mHeadImage!!.exists()){

                GlideUtils.loadLocalImage(this, it.image.originalPath, iv_head_img)
//
            }else{

                ToastUtil.showCenterToast(this,R.string.select_headimage)
            }



        }

    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {


        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam!!.method)
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam
        }

        return type
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)

        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)


    }


}
