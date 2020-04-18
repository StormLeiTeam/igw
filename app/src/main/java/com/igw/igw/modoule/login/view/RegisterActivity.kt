package com.igw.igw.modoule.login.view


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.FileProvider
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.Gravity.BOTTOM
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.igw.igw.BuildConfig
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.NationalityBean
import com.igw.igw.bean.login.CityListBean
import com.igw.igw.bean.login.GenderBean
import com.igw.igw.modoule.login.RegisterContract
import com.igw.igw.modoule.login.model.RegisterModel
import com.igw.igw.modoule.login.presenter.RegisterPresenter
import com.igw.igw.utils.*
import com.igw.igw.widget.ChoicePopWindow
import com.igw.igw.widget.storm.TextClickPrivacy
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
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.common_status_bar.*
import java.io.File
import java.util.*


/**
 * 注册模块
 */

class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterContract.View, TakePhoto.TakeResultListener, InvokeListener {

    companion object {


        val TAG = "RegisterActivity"

        val GENDER_JSON = "[{\"chName\":\"男\",\"enName\":\"女\",\"id\":1,\"isEnglish\":false},{\"chName\":\"女\",\"enName\":\"women\",\"id\":2,\"isEnglish\":false}]"


        public fun startSelf(activity: BaseActivity<*>) {

            var intent = Intent(activity, RegisterActivity::class.java)

            activity.startActivity(intent)

        }


    }


    // 国籍选择器
    private var nationalityPopWindow: WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>? = null

    // 城市
    private var cityPopwindow: WheelViewPopupwindow<CityListBean.DataBean.CitysBean>? = null

    // 性别
    private var genderPopWindow: WheelViewPopupwindow<GenderBean>? = null


    // 生日选择
//    private var birthSelectDialog: DatePickDialog? = null

    //国籍
    private var mCountrys: List<NationalityBean.DataBean.CountrysBean>? = null

    // 城市
    private var mCitys: List<CityListBean.DataBean.CitysBean>? = null

    // 性别
    private var genders: List<GenderBean>? = null

    // head view popWindow
    private var mChiocePopwindow: ChoicePopWindow? = null

    private var takePhoto: TakePhoto? = null
    private var invokeParam: InvokeParam? = null
    private var cropOptions: CropOptions? = null
    private var compressConfig: CompressConfig? = null
    private var imageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        getTakePhoto().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        getTakePhoto().onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)

    }

    private fun getTakePhoto(): TakePhoto {
        if (takePhoto == null) {
            takePhoto = (TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this))) as TakePhoto
        }

        return takePhoto!!
    }

    override fun initView() {


        StatusBarUtils.setDarkMode(this)
        status_bar_main.setTitle("注册")
        getSystemNationality()
        setUpImagePicker()

        setUpPop()
        initData()
        requestData()


        setUserAgreement()
        setUpListener()

    }

    private fun setUpImagePicker() {

        takePhoto = getTakePhoto()
        cropOptions = CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create()
        compressConfig = CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create()
        takePhoto?.onEnableCompress(compressConfig, true)

    }

    private fun initData() {

        mChiocePopwindow = ChoicePopWindow(this, iv_head_img)


        //


//        val list = GsonUtils.getInstance().fromJson<java.util.ArrayList<GenderBean>>(GENDER_JSON, java.util.ArrayList::class.java)
//
//        LogUtils.d(TAG, "list --> ${list[0].getChName()}")


//        var genders = ArrayList<GenderBean>()
//        var man = GenderBean()
//        man.setId(1)
//        man.setChName("男")
//        man.setEnName("女")
//
//        var women = GenderBean()
//        women.setId(2)
//        women.setChName("女")
//        women.setEnName("women")
//
//        genders.add(man)
//        genders.add(women)
//
//
//        var gender = Gender()
//        gender.genders = genders
//
//        var gson = Gson()
//        val toJson = gson.toJson(gender)
//
//
//        val fromJson = GsonUtils.getInstance().fromJson<Gender>(toJson, Gender::class.java)
//
//
//        LogUtils.d(TAG, "${toJson}")
//
//        LogUtils.d(TAG,"${fromJson.genders[0].getChName()}")


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


    /**
     * 获取系统的手机语言状太
     */
    private fun getSystemNationality() {


        val locale = LocaleUtils.getCurrentlocale(this)
        if (locale.equals(Locale.ENGLISH)) {
            LogUtils.d(TAG, "当前用户保存的是english")
            LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_ENGLISH)

        } else {
            LogUtils.d(TAG, "当前用户保存的是 中文")

            LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_CHINESE)

        }


    }

    private fun setUpListener() {


        // 从相册选择图片
        mChiocePopwindow?.setOnButtonChoiceOneListener(object : ChoicePopWindow.OnButtonChoiceOneListener {
            override fun onClick() {

//                var file = File(Environment.getExternalStorageDirectory(), "/temp/${System.currentTimeMillis()}.jpg")
//                if (!file.parentFile.exists()) file.parentFile.mkdirs()
////                return Uri.fromFile(file)
//
//                var contentUri: Uri = FileProvider.getUriForFile(this@RegisterActivity, BuildConfig.APPLICATION_ID.toString() + ".fileprovider", file)
                val imageRri = getImageCropUri()


                  takePhoto?.onPickFromGalleryWithCrop(imageRri, cropOptions)
            }

        })

        mChiocePopwindow?.setOnButtonChoiceTwoListener(object: ChoicePopWindow.OnButtonChoiceTwoListener{
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


        }

        /**
         * 城市确认
         */
        cityPopwindow?.onCommitlistener { data, position ->


        }


        /**
         * 国籍选中监听
         */
        nationalityPopWindow?.onCommitlistener { data, position ->


        }





        tv_select_gender.setOnClickListener {


            if (genderPopWindow != null && !genderPopWindow!!.isShowing) {
                genderPopWindow!!.animationStyle = R.style.pop_anim
                genderPopWindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, UIUtils.getNavigationHeight(this))
            }
        }



        tv_select_city.setOnClickListener {

            SoftInputUtils.hideSoftInput(this, tv_select_city)

            if (cityPopwindow != null && !cityPopwindow!!.isShowing) {
                cityPopwindow!!.animationStyle = R.style.pop_anim
                cityPopwindow!!.showAtLocation(root_main, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, UIUtils.getNavigationHeight(this))
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

    private fun getImageCropUri(): Uri {

        var file = File(Environment.getExternalStorageDirectory(), "/temp/${System.currentTimeMillis()}.jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        return Uri.fromFile(file)


    }


    /**
     * 获取头像图片
     */
    private fun selectHeadImg() {
        // 判断

        if (mChiocePopwindow == null) {

            mChiocePopwindow = ChoicePopWindow(this, iv_head_img)
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
                        BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)

            }
        }


    }


    private var pvTime: TimePickerView? = null
    private var pickerBuilder: TimePickerBuilder? = null

    /**
     *
     */
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

    private fun showDateOfbrith() {
//
//        if (birthSelectDialog == null) {
//            birthSelectDialog = DatePickDialog(this)
//            birthSelectDialog!!.setYearLimt(200)
//            birthSelectDialog!!.setType(DateType.TYPE_YMD)
//            birthSelectDialog!!.setMessageFormat("yyyy-MM-dd")
//            birthSelectDialog!!.setOnChangeLisener(null)
//            birthSelectDialog!!.setOnSureLisener { date ->
//                date?.let {
//
//                    var instance = Calendar.getInstance()
//                    instance.time = date
//
//                    var year = instance.get(Calendar.YEAR)
//                    var month = instance.get(Calendar.MONTH)
//                    var day = instance.get(Calendar.DAY_OF_MONTH)
//
//
////                    String str = TimesUtils.timeToString(year, month, day);
////                    regTime = Long.valueOf(str);
////                    regTime = str;
////                    LogUtils.d(TAG, "获取的时间 --> " + regTime);
////                    tvSelectCarRequestDate.setText(year + "年" + (month + 1) + "月" + day + "日");
//                    val time = year.toString() + "年" + (month + 1) + "月" + day + "日"
//                    val s = TimesUtils.time2StringTime(time, "yyyy年MM月dd日", "yyyy-MM-dd")
//                    tv_select_ymd.text = s
//                }
//            }
//
//
//        }
//
//
//        birthSelectDialog?.show()

    }

    /**
     *  网络请求国籍
     */
    private fun requestData() {

        mPresenter.getNationalityData()
        mPresenter.getCityListData(1)

    }


    /**
     * 创建pop
     */
    private fun setUpPop() {

        // 国籍选择
        nationalityPopWindow = WheelViewPopupwindow<NationalityBean.DataBean.CountrysBean>(this)

        // 城市
        cityPopwindow = WheelViewPopupwindow<CityListBean.DataBean.CitysBean>(this)

        // 性别
        genderPopWindow = WheelViewPopupwindow<GenderBean>(this)


    }


    override fun initPresenter() {

        mPresenter = RegisterPresenter(RegisterModel())
        mPresenter.attachView(this)


    }

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun setTitle(): String {
        return ""
    }

    override fun setRightButton(): String {
        return ""
    }

    override fun setStatusBarColor(): Boolean {

        return true
    }


    fun setUserAgreement() {

        var content = "我同意《i-gw用户协议》"

        val spannable = SpannableStringBuilder(content)
        tv_user_agreement.movementMethod = LinkMovementMethod.getInstance()
        spannable.setSpan(TextClickPrivacy(this), 3, content.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_user_agreement.text = spannable

    }


    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }

    /**
     *  国籍数据
     */
    fun showNationality(countrys: List<NationalityBean.DataBean.CountrysBean>) {

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


    }

    fun showCityListData(citys: List<CityListBean.DataBean.CitysBean>) {


        this.mCitys = citys;

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

    override fun takeSuccess(result: TResult?) {
        LogUtils.d(TAG, "takePhoto --> takeSuccess -> ${result?.image?.originalPath}")

    }

    override fun takeCancel() {
        LogUtils.d(TAG, "takePhoto --> takeCancel")

    }

    override fun takeFail(result: TResult?, msg: String?) {
        LogUtils.d(TAG, "takePhoto --> takeFail -> ${msg}")

    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {

        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam!!.method)
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam
        }

        return type

    }


}
