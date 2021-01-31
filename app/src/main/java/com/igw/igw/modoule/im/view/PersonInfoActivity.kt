package com.igw.igw.modoule.im.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.data.LocalUriFetcher
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder
import com.igw.igw.R
import com.igw.igw.activity.BaseActivity
import com.igw.igw.bean.chat.ChatRoomUsesBean
import com.igw.igw.bean.chat.DelFriendBean
import com.igw.igw.modoule.im.PersonInfoContract
import com.igw.igw.modoule.im.model.PersonInfoModel
import com.igw.igw.modoule.im.presenter.PersonInfoPresenter
import com.igw.igw.utils.GlideUtils
import com.igw.igw.utils.GsonUtils
import com.igw.igw.utils.LocaleUtils
import com.igw.igw.utils.StatusBarUtils
import com.igw.igw.widget.storm.StatusBarView
import com.shengshijingu.yashiji.common.Constants
import com.shengshijingu.yashiji.common.Constants.BASE_URL
import com.shengshijingu.yashiji.common.util.ToastUtil
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_person_info.*
import kotlinx.android.synthetic.main.common_status_bar.*
import org.jetbrains.annotations.Contract

/**
 *
 */
class PersonInfoActivity : BaseActivity<PersonInfoPresenter>(), PersonInfoContract.View {

    companion object {
        val TAG = "PersonInfoActivity"


        public fun startSelf(activity: Activity, value: String) {

            var intent = Intent(activity, PersonInfoActivity::class.java)
            intent.putExtra("person_info", value)
            activity.startActivity(intent)

        }
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_person_info)
//    }

    var userInfo: String? = null

    var bean: ChatRoomUsesBean.DataBean.RoomUsersBean? = null

    override fun initView() {


        StatusBarUtils.setDarkMode(this)

        status_bar_main.setTitle("")
        status_bar_main.setTitleTextSize(16F)
        status_bar_main.setConfirmVisible(View.VISIBLE)
        status_bar_main.setConfirmText("中/En")
        status_bar_main.setConfirmTextColor(R.color.black_000000)
        status_bar_main.setConfirmTextSize(15F)


        userInfo = intent.getStringExtra("person_info")

        bean =
                GsonUtils.instance.fromJson<ChatRoomUsesBean.DataBean.RoomUsersBean>(userInfo, ChatRoomUsesBean.DataBean.RoomUsersBean::class.java)

        updateView(bean!!)

        setUpListener()


    }

    private fun updateView(bean: ChatRoomUsesBean.DataBean.RoomUsersBean) {


        GlideUtils.loadImage(this,
                Constants.BASE_URL + bean.headImage, iv_head_image);

        tv_person_name.text = bean.nickName
        if (bean.sex == 1) {
            iv_sex.setBackgroundResource(R.drawable.man)

        } else {
            iv_sex.setBackgroundResource(R.drawable.girl)

        }
        tv_individual_resume.text = bean.userDesc
        

        if (LocaleUtils.isLocaleEn(this)) {
            // en
            tv_nationality.text = bean.countryEnName
            tv_city.text = bean.cityEnName

            // 学校
//            tv_organization_or_school.text = bean.

        } else {

            //cn
            tv_nationality.text = bean.countryCnName

            tv_city.text = bean.cityCnName
            // 学校
//            tv_organization_or_school.text = bean.


        }
        btn_del_friend.visibility = View.GONE
        btn_send_msg.visibility = View.GONE
        
        // 判断是否为好友
        if (bean.isFriend == 0) {
            btn_del_friend.visibility = View.GONE
            btn_send_msg.visibility = View.GONE
        }else{
            btn_del_friend.visibility = View.VISIBLE
            btn_send_msg.visibility = View.VISIBLE

        }
    }

    private fun setUpListener() {


        btn_del_friend.setOnClickListener {


            bean?.let {
                mPresenter.delFriend(it.userId)

            }

        }


        btn_send_msg.setOnClickListener {

            bean?.let {
                SingleChatActivity.startSelfOfIntent(this,it.userId.toString(),it.nickName, Conversation.ConversationType.PRIVATE)

            }

        }


        status_bar_main.setOnConfirmClickListener(object : StatusBarView.OnConfirmClickListener {
            override fun onClick() {

                userInfo?.let {

                    LocaleUtils.changeLocale(this@PersonInfoActivity, "person_info", it)
                    

                }

            }

        })
    }

    override fun initPresenter() {
        mPresenter = PersonInfoPresenter(PersonInfoModel())
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_person_info


    override fun setTitle(): String {

        return ""
    }

    override fun setRightButton(): String {
        return ""

    }

    override fun setStatusBarColor(): Boolean {
        return false

    }

    override fun delFriendSuccess(data: DelFriendBean.DataBean) {

        ToastUtil.showCenterToast(this, "删除成功")
        finish()

    }

    override fun delFriendFail(code: Int, msg: String) {


        ToastUtil.showCenterToast(this, msg)


    }


    override fun fail(o: Any?) {
    }

    override fun success(o: Any?) {
    }
}